package com.sarapis.model;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Service {
  private UUID id;
  private String name;
  private String alternateName;
  private String description;
  private String url;
  private List<Url> additionalUrls;
  private String email;
  private String status;
  private String interpretationServices;
  private String applicationProcess;
  private String feesDescription;
  private String waitTime;
  private String fees;
  private String accreditations;
  private String eligibilityDescription;
  private int minimumAge;
  private int maximumAge;
  private String assuredDate;
  private String assurerEmail;
  private String licenses;
  private String alert;
  private String lastModified;
  private List<Phone> phones;
  private List<Schedule> schedules;
  private List<ServiceArea> serviceAreas;
  private List<ServiceAtLocation> serviceAtLocations;
  private List<Language> languages;
  private Organization organization;
  private List<Funding> funding;
  private List<CostOption> costOptions;
  private Program program;
  private List<RequiredDocument> requiredDocuments;
  private List<Contact> contacts;
  private List<ServiceCapacity> capacities;
  private List<Attribute> attributes;
  private List<Metadata> metadata;
}
