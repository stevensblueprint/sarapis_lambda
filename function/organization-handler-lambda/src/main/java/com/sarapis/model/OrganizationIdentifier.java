package com.sarapis.model;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrganizationIdentifier {
  private UUID id;
  private String identifierScheme;
  private String identifierType;
  private String identifier;
  private List<Attribute> attributes;
  private List<Metadata> metadata;
}
