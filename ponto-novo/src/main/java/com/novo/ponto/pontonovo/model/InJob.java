package com.novo.ponto.pontonovo.model;

import java.time.LocalDateTime;

public class InJob {
    private LocalDateTime entranceHour;

    public InJob(LocalDateTime entranceHour) {
        this.entranceHour = entranceHour;
    }

    public LocalDateTime getEntranceHour() {
        return entranceHour;
    }
}
