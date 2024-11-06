package com.sarapis.lambda;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.sarapis.model.Organization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.lambda.powertools.logging.Logging;

import java.util.Map;


public class OrganizationHandlerLambda implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent>{
    private static final Logger logger = LoggerFactory.getLogger(OrganizationHandlerLambda.class);
    private static final Map<String, String> HEADERS = Map.of("Content-Type", "application/json");
    private static final String DOB_TABLE_NAME = "organizations";
    private static final DynamoDbClient dynamoDbClient =
            DynamoDbClient.builder()
                    .region(Region.US_EAST_1)
                    .build();

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

    private Organization getOrganizationById(String orgId) {
        return null;
    }

}
