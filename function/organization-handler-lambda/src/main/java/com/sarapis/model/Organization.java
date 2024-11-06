package com.sarapis.model;

import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Organization {
    private UUID id;
    private String name;
    private String alternateName;
    private String description;
    private String email;
    private String url;
    private String taxStatus;
    private String taxId;
    private Date yearIncorporated;
    private String legalStatus;
    private Program program;
    private Service service;
    private List<OrganizationLocation> locations;
    private Phone phone;
}
