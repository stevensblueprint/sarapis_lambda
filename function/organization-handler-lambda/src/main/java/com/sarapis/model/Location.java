package com.sarapis.model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Location {
    private String description;
    private Phone phone;
}
