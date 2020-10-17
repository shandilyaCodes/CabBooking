package com.shandilya.chalo.model;

import com.shandilya.chalo.dto.RiderDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class Rider {
    private String uniqueId;
    private String riderName;

    public RiderDTO mapToRiderDTO() {
        return RiderDTO.builder()
                .uniqueId(this.uniqueId)
                .riderName(this.riderName)
                .build();
    }
}