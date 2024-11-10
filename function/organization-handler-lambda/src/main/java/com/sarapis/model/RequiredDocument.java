package com.sarapis.model;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequiredDocument {
  private UUID id;
  private String document;
  private String uri;
  private List<Attribute> attributes;
  private List<Metadata> metadata;
}
