package com.sarapis.model;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Language {
    private UUID id;
    private String name;
    private String code;
    private String note;
    private List<Attribute> attributes;
    private List<Metadata> metadata;
}
