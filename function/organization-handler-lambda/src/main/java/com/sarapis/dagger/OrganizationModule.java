package com.sarapis.dagger;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sarapis.service.OrganizationService;
import com.sarapis.service.OrganizationServiceImpl;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import javax.inject.Singleton;

@Module
public class OrganizationModule {

    @Provides
    @Singleton
    @Named("DB_URI")
    public String provideDbUri() {
        return System.getenv().getOrDefault("DB_URI", "http://host.docker.internal:8000");
    }

    @Provides
    @Singleton
    @Named("ACCESS_KEY")
    public String provideAccessKey() {
        return System.getenv().getOrDefault("ACCESS_KEY", "FAKEID");
    }

    @Provides
    @Singleton
    @Named("SECRET_KEY")
    public String provideSecretKey() {
        return System.getenv().getOrDefault("SECRET_KEY", "FAKEKEY");
    }

    @Provides
    @Singleton
    @Named("REGION")
    public String provideRegion() {
        return System.getenv().getOrDefault("REGION", "us-east-1");
    }

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
    @Named("amazonDynamoDB")
    public AmazonDynamoDB provideAmazonDynamoDB(@Named("DB_URI") String dbUri, @Named("ACCESS_KEY") String accessKey,
            @Named("SECRET_KEY") String secretKey, @Named("REGION") String region) {
        return AmazonDynamoDBClient.builder().withEndpointConfiguration(new EndpointConfiguration(dbUri, region))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .build();
    }

    @Provides
    @Singleton
    public OrganizationService provideOrganizationService(@Named("amazonDynamoDB") AmazonDynamoDB amazonDynamoDB) {
        return new OrganizationServiceImpl(new DynamoDBMapper(amazonDynamoDB));
    }
}
