package com.sarapis.model;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CostOption {
    private UUID id;
    private String validFrom;
    private String validTo;
    private String option;
    private String currency;
    private int amount;
    private String amountDescription;
    private List<Attribute> attributes;
    private List<Metadata> metadata;
}
