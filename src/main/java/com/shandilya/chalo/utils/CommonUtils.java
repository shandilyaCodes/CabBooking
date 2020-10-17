package com.shandilya.chalo.utils;

import com.shandilya.chalo.model.Location;
import org.springframework.stereotype.Component;

@Component
public class CommonUtils {

    public static Location stringToLocation(String location) {
        if (null != location) {
            final String[] axis = location.split(",");
            return Location.builder()
                    .x(Double.parseDouble(axis[0]))
                    .y(Double.parseDouble(axis[1]))
                    .build();
        }
        return null;
    }

    public static String locationToString(Location location) {
        return location.getX()+","+location.getY();
    }
}