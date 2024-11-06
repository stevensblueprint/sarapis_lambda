package com.sarapis.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Program {
    private String name;
    private String alternateName;
    private Service service;
}
