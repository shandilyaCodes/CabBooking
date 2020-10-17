package com.shandilya.chalo.strategy;

import com.shandilya.chalo.model.Cab;
import com.shandilya.chalo.model.Location;
import com.shandilya.chalo.model.Rider;
import org.springframework.stereotype.Component;
import java.util.List;

public interface CabMatchingStrategy {
    Cab matchCabToRider(Rider rider, List<Cab> candidateCabs, Location source, Location destination);
}