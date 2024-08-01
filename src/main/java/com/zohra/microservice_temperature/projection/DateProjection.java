package com.zohra.microservice_temperature.projection;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zohra.microservice_temperature.utils.DateTimeUtils;

import java.time.LocalDateTime;

public class DateProjection {
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime date;

    public DateProjection() {
    }

    public DateProjection(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void roundToNearestTen() {
        this.date = DateTimeUtils.roundMinutesToNearestTen(this.date);
    }
}
