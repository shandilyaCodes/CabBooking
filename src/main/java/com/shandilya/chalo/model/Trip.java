package com.shandilya.chalo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Trip {
    private String riderUniqueId;
    private String licencePlateNumber;
    private String tripStatus;
    private Double fare;
    private Location source;
    private Location destination;
}