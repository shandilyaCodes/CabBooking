package com.shandilya.chalo.dto;

import com.shandilya.chalo.model.Trip;
import com.shandilya.chalo.utils.CommonUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Trips")
public class TripDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tripId;
    private String riderUniqueId;
    private String cabLicencePlateNumber;
    private Double fare;
    private String sourceLocation;
    private String destinationLocation;
    private String tripStatus;

    public Trip mapToTrip() {
        return Trip.builder()
                .riderUniqueId(riderUniqueId)
                .licencePlateNumber(cabLicencePlateNumber)
                .fare(fare)
                .source(CommonUtils.stringToLocation(sourceLocation))
                .destination(CommonUtils.stringToLocation(destinationLocation))
                .tripStatus(tripStatus)
                .build();
    }
}