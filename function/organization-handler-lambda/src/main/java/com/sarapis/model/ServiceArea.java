package com.sarapis.model;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceArea {
  private UUID id;
  private String name;
  private String description;
  private String extent;
  private String extentType;
  private String uri;
  private List<Attribute> attributes;
  private List<Metadata> metadata;
}
