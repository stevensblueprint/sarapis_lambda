package com.sarapis.lambda;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.sarapis.model.Organization;
import com.sarapis.service.OrganizationService;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class OrganizationHandlerLambdaTest {
    @InjectMocks
    private OrganizationHandlerLambda organizationHandlerLambda;

    @Mock
    private OrganizationService organizationService;

    @Mock
    private Context context;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testHandleRequestGET() throws Exception {
        // Given
        APIGatewayProxyRequestEvent event = new APIGatewayProxyRequestEvent();
        event.setHttpMethod("GET");
        event.setPath("/organizations");
        event.setHeaders(Map.of("Authorization", "Bearer token"));

        // When
        organizationHandlerLambda.handleRequest(event, context);
        verify(organizationService, times(1)).getAllOrganizations();
    }

    @Test
    void testHandleRequestPOST() throws Exception {
        // Given
        APIGatewayProxyRequestEvent event = new APIGatewayProxyRequestEvent();
        event.setHttpMethod("POST");
        event.setPath("/organizations");
        event.setHeaders(Map.of("Authorization", "Bearer token"));
        event.setBody("{\"name\": \"Test Organization\"}");

        // When
        organizationHandlerLambda.handleRequest(event, context);
        verify(organizationService, times(1)).createOrganization(any(Organization.class));
    }

    @Test
    void testHandleRequestPUT() {
        // Given
        String organizationId = "12345";
        APIGatewayProxyRequestEvent event = new APIGatewayProxyRequestEvent();
        event.setHttpMethod("PUT");
        event.setPath("/organizations/" + organizationId);
        event.setHeaders(Map.of("Authorization", "Bearer token"));
        event.setBody("{\"name\": \"Updated Organization\"}");
        event.setPathParameters(Map.of("id", organizationId));

        // When
        organizationHandlerLambda.handleRequest(event, context);
        verify(organizationService, times(1)).updateOrganization(eq(organizationId), any(Organization.class));
    }

    @Test
    void testHandleRequestDELETE() {
        // Given
        String organizationId = "12345";
        APIGatewayProxyRequestEvent event = new APIGatewayProxyRequestEvent();
        event.setHttpMethod("DELETE");
        event.setPath("/organizations/" + organizationId);
        event.setHeaders(Map.of("Authorization", "Bearer token"));
        event.setPathParameters(Map.of("id", organizationId));

        // When
        organizationHandlerLambda.handleRequest(event, context);
        verify(organizationService, times(1)).deleteOrganization(eq(organizationId));
    }
}
