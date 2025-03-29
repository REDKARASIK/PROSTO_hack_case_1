package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class EventRequest {
    @NotBlank(message = "Название обязательно")
    private String title;

    @NotNull(message = "Время начала обязательно")
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime startTime;

    @NotNull(message = "Время окончания обязательно")
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime endTime;

    // Геттеры и сеттеры
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
}