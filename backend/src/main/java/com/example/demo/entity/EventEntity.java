package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "event_info")
@Data
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_info_id")
    private Long id;

    @Column(name = "event_title", nullable = false, length = 100)
    private String title;

    @Column(name = "time_start", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "time_end", nullable = false)
    private LocalDateTime endTime;
}