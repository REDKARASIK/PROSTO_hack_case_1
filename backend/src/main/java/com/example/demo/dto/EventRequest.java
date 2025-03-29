package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class EventRequest {
    @NotBlank(message = "Название обязательно")
    private String title;

    @NotNull(message = "Дата начала обязательна")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate dateStart;

    @NotNull(message = "Время начала обязательно")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime timeStart;

    @NotNull(message = "Дата окончания обязательна")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate dateEnd;

    @NotNull(message = "Время окончания обязательно")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime timeEnd;

    // Геттеры и сеттеры
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