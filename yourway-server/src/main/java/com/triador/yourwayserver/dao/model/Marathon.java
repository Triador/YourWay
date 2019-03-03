package com.triador.yourwayserver.dao.model;

import java.time.LocalDate;

public class Marathon {

    private int marathonId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;

    public int getMarathonId() {
        return marathonId;
    }

    public void setMarathonId(int marathonId) {
        this.marathonId = marathonId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
