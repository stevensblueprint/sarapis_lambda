package com.sarapis.model;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceCapacity {
  private UUID id;
  private Unit unit;
  private int available;
  private int maximum;
  private String description;
  private String updated;
  private List<Attribute> attributes;
  private List<Metadata> metadata;
}
