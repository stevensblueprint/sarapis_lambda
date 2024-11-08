package com.sarapis.model;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaxonomyTerm {
    private UUID id;
    private String code;
    private String name;
    private String description;
    private UUID parentId;
    private String taxonomy;
    private TaxonomyTerm taxonomyDetail;
    private String language;
    private String termUri;
    private List<Metadata> metadata;
}
