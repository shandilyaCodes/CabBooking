package com.shandilya.chalo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Booking {
    private Long riderId;
    private Location source;
    private Location destination;
}