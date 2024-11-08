package com.sarapis.model;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
    private UUID id;
    private String attention;
    private String address1;
    private String address2;
    private String city;
    private String region;
    private String stateProvince;
    private String postalCode;
    private String country;
    private String addressType;
    private List<Attribute> attributes;
    private List<Metadata> metadata;
}
