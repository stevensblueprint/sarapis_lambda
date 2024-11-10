package com.sarapis.model;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Taxonomy {
    private UUID id;
    private String name;
    private String description;
    private String uri;
    private String version;
    private List<Metadata> metadata;
}
