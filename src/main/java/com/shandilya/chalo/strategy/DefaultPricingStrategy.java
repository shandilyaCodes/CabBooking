package com.shandilya.chalo.strategy;

import com.shandilya.chalo.model.Location;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class DefaultPricingStrategy implements PricingStrategy {
    @Override
    public Double findPrice(
            @NonNull final Location source,
            @NonNull final Location destination) {
        return source.distance(destination);
    }
}