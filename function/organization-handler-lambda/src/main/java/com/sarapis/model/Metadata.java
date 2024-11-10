package com.sarapis.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Metadata {
    private UUID id;
    private String resourceType;
    private String lastActionDate;
    private String lastActionType;
    private String fieldName;
    private String previousValue;
    private String replacementValue;
    private String updatedBy;
}
