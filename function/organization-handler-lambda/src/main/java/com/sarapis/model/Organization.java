package com.sarapis.model;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Organization {
    private UUID id;
    private String name;
    private String alternateName;
    private String description;
    private String email;
    private String website;
    private List<Url> additionalWebsites;
    private String taxStatus; // Deprecated
    private String taxId; // Deprecated
    private int yearIncorporated;
    private String legalStatus;
    private String logo;
    private String uri;
    private UUID parentOrganizationId;
    private List<Funding> funding;
    private List<Contact> contacts;
    private List<Phone> phones;
    private List<Location> locations;
    private List<Program> programs;
    private List<OrganizationIdentifier> organizationIdentifiers;
    private List<Attribute> attributes;
    private List<Metadata> metadata;
}
