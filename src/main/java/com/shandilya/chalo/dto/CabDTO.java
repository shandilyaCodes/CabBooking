package com.shandilya.chalo.dto;

import com.shandilya.chalo.model.Cab;
import com.shandilya.chalo.utils.CommonUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Cab")
public class CabDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cabId;
    private String driverName;
    private String currentLocation;
    private Boolean isAvailable;

    public Cab mapToCab() {
        return Cab.builder()
                .id(cabId)
                .currentLocation(CommonUtils.stringToLocation(currentLocation))
                .driverName(driverName)
                .isAvailable(isAvailable)
                .build();
    }
}