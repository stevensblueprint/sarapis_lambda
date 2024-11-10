package com.sarapis.model;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Attribute {
    private UUID id;
    private String linkType;
    private String linkEntity;
    private String value;
    private TaxonomyTerm taxonomyTerm;
    private List<Metadata> metadata;
    private String label;
}
