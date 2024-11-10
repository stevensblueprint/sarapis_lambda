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

import javax.inject.Named;
import javax.inject.Singleton;
import java.net.URI;

@Module
public class OrganizationModule {

  @Provides
  @Singleton
  @Named("DB_URI")
  public String provideDbUri() {
    return System.getenv().getOrDefault("DB_URI", "http://localhost:8080");
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
  public DynamoDbClient provideDynamoDbClient(@Named("DB_URI") String dbUri,
      @Named("ACCESS_KEY") String accessKey, @Named("SECRET_KEY") String secretKey) {
    return DynamoDbClient.builder().endpointOverride(URI.create(dbUri))
        .httpClient(UrlConnectionHttpClient.builder().build()).region(Region.US_EAST_1)
        .credentialsProvider(
            StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)))
        .build();
  }
}
