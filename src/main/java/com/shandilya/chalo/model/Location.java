package com.shandilya.chalo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

@Getter
@Builder
@AllArgsConstructor
public class Location {
    private Double x;
    private Double y;

    public Double distance(Location destination) {
        return sqrt(pow(this.x - destination.x, 2) + pow(this.y - destination.y, 2));
    }
}