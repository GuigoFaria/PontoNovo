package com.novo.ponto.pontonovo.model;

import java.time.LocalDateTime;

public class OutJob {
    private LocalDateTime exitHour;

    public OutJob(LocalDateTime exitHour) {
        this.exitHour = exitHour;
    }

    public LocalDateTime getExitHour() {
        return exitHour;
    }
}
