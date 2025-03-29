package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public class EventResponse {
    private Long id;
    private String title;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime startTime;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime endTime;

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
}