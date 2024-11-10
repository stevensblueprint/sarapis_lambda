package com.sarapis.model;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Location {
    private UUID id;
    private String locationType;
    private String url;
    private String name;
    private String alternateName;
    private String description;
    private String transportation;
    private String latitude;
    private String longitude;
    private String externalIdentifierType;
    private List<Language> languages;
    private List<Address> addresses;
    private List<Contact> contacts;
    private List<Accessibility> accessibility;
    private List<Phone> phones;
    private List<Schedule> schedules;
    private List<Attribute> attributes;
    private List<Metadata> metadata;
}
