package com.sarapis.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrganizationLocation extends Location {
    private String name;
    private String alternateName;
    private String description;
    private String transportation;
    private String latitude;
    private String longitude;
}
