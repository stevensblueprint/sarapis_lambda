package com.sarapis.model;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Phone {
    private UUID id;
    private String number;
    private int extension;
    private String type;
    private String description;
    private List<Language> languages;
    private List<Attribute> attributes;
    private List<Metadata> metadata;
}
