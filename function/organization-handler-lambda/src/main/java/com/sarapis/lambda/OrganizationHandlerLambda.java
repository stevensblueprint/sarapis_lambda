package com.sarapis.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sarapis.dagger.DaggerOrganizationComponent;
import com.sarapis.dagger.OrganizationComponent;
import com.sarapis.exceptions.OrganizationNotFoundException;
import com.sarapis.exceptions.OrganizationServiceLogicException;
import com.sarapis.model.Organization;
import com.sarapis.service.OrganizationService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.lambda.powertools.logging.Logging;

import javax.inject.Inject;
import java.util.Map;

public class OrganizationHandlerLambda
        implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    private static final Logger logger = LoggerFactory.getLogger(OrganizationHandlerLambda.class);
    private static final Map<String, String> HEADERS = Map.of("Content-Type", "application/json");

    @Inject
    ObjectMapper objectMapper;

    @Inject
    OrganizationService organizationService;

    public OrganizationHandlerLambda() {
        OrganizationComponent component = DaggerOrganizationComponent.create();
        component.inject(this);
    }

        /**
     * Handles incoming API Gateway requests for organization-related operations.
     * This method routes the request to the appropriate handler based on the HTTP method
     * and path parameters.
     *
     * @param event   The APIGatewayProxyRequestEvent containing information about the HTTP request,
     *                including method, path parameters, and body.
     * @param context The Context object provides methods to access information about the
     *                current execution environment.
     * @return APIGatewayProxyResponseEvent An object containing the response to be sent back
     *         to the client, including status code, headers, and body.
     */
    @Override
    @Logging(clearState = true)
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {
        APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent().withHeaders(HEADERS);
        try {
            logger.info("Received HTTP request: {}", event.getHttpMethod());
            String HTTPMethod = event.getHttpMethod();
            Map<String, String> pathParameters = event.getPathParameters();
            switch (HTTPMethod) {
            case "GET":
                if (pathParameters != null && pathParameters.containsKey("id")) {
                    return handleGetOrganizationById(event, responseEvent);
                }
                return handleGetAllOrganizations(event, responseEvent);
            case "POST":
                return handleCreateOrganization(event, responseEvent);
            case "PUT":
                return handleUpdateOrganization(event, responseEvent);
            case "DELETE":
                return handleDeleteOrganization(event, responseEvent);
            default:
                logger.error("Unsupported HTTP method: {}", HTTPMethod);
                responseEvent.setBody("Unsupported HTTP method");
                responseEvent.setStatusCode(405);
                return responseEvent;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            responseEvent.setBody(e.getMessage());
            responseEvent.setStatusCode(500);
        }
        return responseEvent;
    }

    private APIGatewayProxyResponseEvent handleGetAllOrganizations(APIGatewayProxyRequestEvent requestEvent,
            APIGatewayProxyResponseEvent responseEvent) {
        try {
            List<Organization> organizations = organizationService.getAllOrganizations();
            responseEvent.setBody(objectMapper.writeValueAsString(organizations));
            responseEvent.setStatusCode(200);
            return responseEvent;
        } catch (OrganizationServiceLogicException | JsonProcessingException e) {
            logger.error("Failed to get all organizations: {}", e.getMessage());
            responseEvent.setBody(e.getMessage());
            responseEvent.setStatusCode(500);
            return responseEvent;
        }
    }

    private APIGatewayProxyResponseEvent handleGetOrganizationById(APIGatewayProxyRequestEvent requestEvent,
            APIGatewayProxyResponseEvent responseEvent) {
        String id = requestEvent.getPathParameters().get("id");
        try {
            Organization organization = organizationService.getOrganizationById(id);
            responseEvent.setBody(objectMapper.writeValueAsString(organization));
            responseEvent.setStatusCode(200);
            return responseEvent;
        } catch (OrganizationNotFoundException e) {
            logger.error("Organization not found: {}", id);
            responseEvent.setBody(e.getMessage());
            responseEvent.setStatusCode(404);
            return responseEvent;
        } catch (OrganizationServiceLogicException | JsonProcessingException e) {
            logger.error("Failed to retrieve organization with id {}", id);
            responseEvent.setBody(e.getMessage());
            responseEvent.setStatusCode(500);
            return responseEvent;
        }
    }

    private APIGatewayProxyResponseEvent handleCreateOrganization(APIGatewayProxyRequestEvent requestEvent,
            APIGatewayProxyResponseEvent responseEvent) {
        try {
            Organization organization = objectMapper.readValue(requestEvent.getBody(), Organization.class);
            organization = organizationService.createOrganization(organization);
            responseEvent.setBody(objectMapper.writeValueAsString(organization));
            responseEvent.setStatusCode(201);
            return responseEvent;
        } catch (OrganizationServiceLogicException | JsonProcessingException e) {
            logger.error("Failed to create organization: {}", e.getMessage());
            responseEvent.setBody(e.getMessage());
            responseEvent.setStatusCode(500);
            return responseEvent;
        }
    }

    private APIGatewayProxyResponseEvent handleUpdateOrganization(APIGatewayProxyRequestEvent requestEvent,
            APIGatewayProxyResponseEvent responseEvent) {
        String id = requestEvent.getPathParameters().get("id");
        try {
            Organization organization = objectMapper.readValue(requestEvent.getBody(), Organization.class);
            Organization updatedOrganization = organizationService.updateOrganization(id, organization);
            responseEvent.setBody(objectMapper.writeValueAsString(updatedOrganization));
            responseEvent.setStatusCode(200);
            return responseEvent;
        } catch (JsonProcessingException e) {
            logger.error("Failed to update organization with id {}: {}", id, e.getMessage());
            responseEvent.setBody(e.getMessage());
            responseEvent.setStatusCode(500);
            return responseEvent;
        }
    }

    private APIGatewayProxyResponseEvent handleDeleteOrganization(APIGatewayProxyRequestEvent requestEvent,
            APIGatewayProxyResponseEvent responseEvent) {
        String id = requestEvent.getPathParameters().get("id");
        try {
            organizationService.deleteOrganization(id);
            responseEvent.setBody("Organization deleted successfully");
            responseEvent.setStatusCode(204);
            return responseEvent;
        } catch (Exception e) {
            logger.error("Failed to delete organization with id {}: {}", id, e.getMessage());
            responseEvent.setBody(e.getMessage());
            responseEvent.setStatusCode(500);
            return responseEvent;
        }
    }
}
