package com.sarapis.dagger;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import dagger.Module;
import dagger.Provides;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;


import javax.inject.Singleton;
import java.net.URI;

@Module
public class OrganizationModule {

    @Provides
    @Singleton
    public ObjectMapper provideObjectMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }

    @Provides
    @Singleton
    public DynamoDbClient provideDynamoDbClient() {
        String DB_URI = "http://localhost:8080";
        String DUMMY_ACCESS_KEY = "FAKEID";
        String DUMMY_SECRET_KEY = "FAKEKEY";
        return DynamoDbClient.builder()
                .endpointOverride(URI.create(DB_URI))
                .httpClient(UrlConnectionHttpClient.builder().build())
                .region(Region.US_EAST_1)
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(DUMMY_ACCESS_KEY,DUMMY_SECRET_KEY)))
                .build();
    }
}
