package com.sarapis.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrganizationLocation extends Location {
    private String name;
    private String alternateName;
    private String description;
    private String transportation;
    private String latitude;
    private String longitude;
}
