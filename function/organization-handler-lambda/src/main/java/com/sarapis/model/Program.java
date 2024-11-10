package com.sarapis.model;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Program {
    private UUID id;
    private String name;
    private String alternateName;
    private String description;
    private List<Attribute> attributes;
    private List<Metadata> metadata;
}
