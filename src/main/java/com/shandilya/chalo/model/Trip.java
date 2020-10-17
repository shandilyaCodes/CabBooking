package com.shandilya.chalo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Trip {
    private Long riderId;
    private Long cabId;
    private String tripStatus;
    private Double fare;
    private Location source;
    private Location destination;
}