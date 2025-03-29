package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "event_info", schema = "events")  // Исправлено на "events" (как в вашей БД)
@Data
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_info_id")
    private Long id;

    @Column(name = "event_title", nullable = false, length = 100)
    private String title;

    @Column(name = "date_start", nullable = false)
    private LocalDate dateStart;

    @Column(name = "time_start", nullable = false)
    private LocalTime timeStart;

    @Column(name = "date_end", nullable = false)
    private LocalDate dateEnd;

    @Column(name = "time_end", nullable = false)
    private LocalTime timeEnd;

//    // Дополнительные методы для удобства работы
//    public LocalDateTime getCombinedStartDateTime() {
//        return LocalDateTime.of(dateStart, timeStart);
//    }
//
//    public LocalDateTime getCombinedEndDateTime() {
//        return LocalDateTime.of(dateEnd, timeEnd);
//    }
//
//    public void setFromStartDateTime(LocalDateTime startDateTime) {
//        this.dateStart = startDateTime.toLocalDate();
//        this.timeStart = startDateTime.toLocalTime();
//    }
//
//    public void setFromEndDateTime(LocalDateTime endDateTime) {
//        this.dateEnd = endDateTime.toLocalDate();
//        this.timeEnd = endDateTime.toLocalTime();
//    }
}