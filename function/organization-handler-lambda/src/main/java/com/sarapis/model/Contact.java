package com.sarapis.model;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contact {
  private UUID id;
  private String name;
  private String title;
  private String department;
  private String email;
  private List<Phone> phones;
  private List<Attribute> attributes;
  private List<Metadata> metadata;
}
