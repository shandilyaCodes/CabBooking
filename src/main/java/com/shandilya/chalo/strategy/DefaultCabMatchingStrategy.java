package com.shandilya.chalo.strategy;

import com.shandilya.chalo.model.Cab;
import com.shandilya.chalo.model.Location;
import com.shandilya.chalo.model.Rider;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultCabMatchingStrategy implements CabMatchingStrategy {
    @Override
    public Cab matchCabToRider(
            @NonNull final Rider rider,
            @NonNull final List<Cab> candidateCabs,
            @NonNull final Location source,
            @NonNull final Location destination) {
        if (candidateCabs.isEmpty())
            return null;

        return candidateCabs.get(0);
    }
}
