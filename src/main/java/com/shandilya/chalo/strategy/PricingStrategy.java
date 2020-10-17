package com.shandilya.chalo.strategy;

import com.shandilya.chalo.model.Location;

public interface PricingStrategy {
    Double findPrice(Location source, Location destination);
}