package com.sarapis.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@DynamoDBTable(tableName = "organizations")
public class Organization {
    @DynamoDBHashKey(attributeName = "id")
    @DynamoDBAutoGeneratedKey
    private String id;
    private String name;
    private String alternateName;
    private String description;
    private String email;
    private String website;
    private String taxStatus; // Deprecated
    private String taxId; // Deprecated
    private int yearIncorporated;
    private String legalStatus;
    private String logo;
    private String uri;
    // private List<Funding> funding;
    // private List<Contact> contacts;
    // private List<Phone> phones;
    // private List<Location> locations;
    // private List<Program> programs;
    // private List<OrganizationIdentifier> organizationIdentifiers;
    // private List<Attribute> attributes;
    // private List<Metadata> metadata;

}
