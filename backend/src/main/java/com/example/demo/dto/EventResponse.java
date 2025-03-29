package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalTime;

public class EventResponse {
    private Long id;
    private String title;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate dateStart;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime timeStart;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate dateEnd;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime timeEnd;

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public LocalDate getDateStart() { return dateStart; }
    public void setDateStart(LocalDate dateStart) { this.dateStart = dateStart; }

    public LocalTime getTimeStart() { return timeStart; }
    public void setTimeStart(LocalTime timeStart) { this.timeStart = timeStart; }

    public LocalDate getDateEnd() { return dateEnd; }
    public void setDateEnd(LocalDate dateEnd) { this.dateEnd = dateEnd; }

    public LocalTime getTimeEnd() { return timeEnd; }
    public void setTimeEnd(LocalTime timeEnd) { this.timeEnd = timeEnd; }

}