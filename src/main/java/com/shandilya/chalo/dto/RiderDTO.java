package com.shandilya.chalo.dto;

import com.shandilya.chalo.model.Rider;
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
@Table(name = "Rider")
public class RiderDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long riderId;
    private String uniqueId;
    private String riderName;

    public Rider mapToRider() {
        return Rider.builder()
                .uniqueId(this.uniqueId)
                .riderName(this.riderName)
                .build();
    }
}