package com.sarapis.model;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Accessibility {
    private UUID id;
    private String description;
    private String details;
    private String url;
    private List<Attribute> attributes;
    private List<Metadata> metadata;
}
