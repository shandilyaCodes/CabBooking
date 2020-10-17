package com.shandilya.chalo.model;

import com.shandilya.chalo.dto.CabDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Cab {
    private String licencePlate;
    private String driverName;
    private Location currentLocation;
    private Boolean isAvailable;

    public CabDTO mapToCabDTO() {
        return CabDTO.builder()
                .licencePlate(this.licencePlate)
                .driverName(this.driverName)
                .isAvailable(this.isAvailable)
                .build();
    }
}