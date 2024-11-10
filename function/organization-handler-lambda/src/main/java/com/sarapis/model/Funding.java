package com.sarapis.model;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Funding {
  private UUID id;
  private String source;
  private List<Attribute> attributes;
  private List<Metadata> metadata;
}
