package com.sarapis.model;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Unit {
    private UUID id;
    private String name;
    private String scheme;
    private String identifier;
    private String uri;
    private List<Attribute> attributes;
    private List<Metadata> metadata;
}
