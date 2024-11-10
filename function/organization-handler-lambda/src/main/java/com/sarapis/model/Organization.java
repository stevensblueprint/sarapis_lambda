package com.sarapis.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamoDBTable(tableName = "organizations")
public class Organization {
    @DynamoDBHashKey(attributeName = "id")
    @DynamoDBAutoGeneratedKey
    private UUID id;

    @DynamoDBAttribute
    private String name;

    @DynamoDBAttribute
    private String alternateName;

    @DynamoDBAttribute
    private String description;

    @DynamoDBAttribute
    private String email;

    @DynamoDBAttribute
    private String website;

    @DynamoDBAttribute
    private String taxStatus; // Deprecated

    @DynamoDBAttribute
    private String taxId; // Deprecated

    @DynamoDBAttribute
    private int yearIncorporated;

    @DynamoDBAttribute
    private String legalStatus;

    @DynamoDBAttribute
    private String logo;

    @DynamoDBAttribute
    private String uri;

}
