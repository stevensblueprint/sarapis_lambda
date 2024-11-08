package com.sarapis.lambda;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sarapis.dagger.DaggerOrganizationComponent;
import com.sarapis.dagger.OrganizationComponent;
import com.sarapis.model.Organization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.lambda.powertools.logging.Logging;

import javax.inject.Inject;
import java.util.Map;


public class OrganizationHandlerLambda implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent>{
    private static final Logger logger = LoggerFactory.getLogger(OrganizationHandlerLambda.class);
    private static final Map<String, String> HEADERS = Map.of("Content-Type", "application/json");
    private static final String DOB_TABLE_NAME = "organizations";

    @Inject
    ObjectMapper objectMapper;

    @Inject
    DynamoDbClient dynamoDbClient;

    public OrganizationHandlerLambda() {
        OrganizationComponent component = DaggerOrganizationComponent.create();
        component.inject(this);
    }

    @Override
    @Logging(clearState = true)
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {
        APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent()
                .withHeaders(HEADERS);
        try {
            logger.info("Received HTTP request: {}", event.getHttpMethod());
            String HTTPMethod = event.getHttpMethod();
            switch (HTTPMethod) {
                case "GET":
                    logger.info("Received GET request: {}", event.getBody());
                    try {
                        Organization organization = objectMapper.readValue(event.getBody(), Organization.class);
                    } catch (JsonProcessingException e) {
                        responseEvent.setBody(event.getBody());
                        responseEvent.setStatusCode(400);
                    }
                case "POST":
                    logger.info("Received POST request: {}", event.getBody());
                case "PUT":
                    logger.info("Received PUT request: {}", event.getBody());
                case "DELETE":
                    logger.info("Received DELETE request: {}", event.getBody());
                default:
                    logger.error("Unsupported HTTP method: {}", HTTPMethod);
                    responseEvent.setBody("Unsupported HTTP method");
                    responseEvent.setStatusCode(405);
            }
            return responseEvent;

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            responseEvent.setBody(e.getMessage());
            responseEvent.setStatusCode(500);
        }
        return responseEvent;
    }

}
