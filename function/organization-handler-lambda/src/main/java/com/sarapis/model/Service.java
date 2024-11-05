package com.sarapis.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Service {
    private String name;
    private String alternateName;
    private String description;
    private String url;
    private String email;
    private String status;
    private String interpretationServices;
    private String interpretationProcess;
    private String waitTime;
    private String fees;
    private String accreditations;
    private String licenses;
    private List<Taxonomy> taxonomy;
    private Location location;
    private Phone phone;
}
