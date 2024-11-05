package com.sarapis.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Phone {
    private String number;
    private String extension;
    private String type;
    private String language;
    private String description;
}
