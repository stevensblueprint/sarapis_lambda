package com.sarapis.model;

import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Schedule {
    private UUID id;
    private Date validFrom;
    private Date validTo;
    private Date dtstart;
    private int timezone;
    private Date until;
    private Date count;
    private Date wkst;
    private String freq;
    private int interval;
    private String byDay;
    private String byWeekNo;
    private String byMonth;
    private String byYearDay;
    private String description;
    private Date opensAt;
    private Date closesAt;
    private String scheduleLink;
    private String attendingType;
    private String notes;
    private List<Attribute> attributes;
    private List<Metadata> metadata;
}
