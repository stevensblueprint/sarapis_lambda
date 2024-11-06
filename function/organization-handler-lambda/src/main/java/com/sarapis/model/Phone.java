package com.sarapis.model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Phone {
    private String number;
    private String extension;
    private String type;
    private String language;
    private String description;
}
