package com.sarapis.model;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceAtLocation {
  private UUID id;
  private String name;
  private List<ServiceArea> serviceAreas;
  private List<Contact> contacts;
  private List<Phone> phones;
  private List<Schedule> schedules;
  private Location location;
  private List<Attribute> attributes;
  private List<Metadata> metadata;
}
