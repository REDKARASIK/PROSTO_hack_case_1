package com.example.demo.repository;

import com.example.demo.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {

    // Найти события по дате начала
    List<EventEntity> findByDateStart(LocalDate dateStart);

    // Найти события между датами (включительно)
    List<EventEntity> findByDateStartBetween(LocalDate startDate, LocalDate endDate);

    // Найти события после указанной даты
    List<EventEntity> findByDateStartAfter(LocalDate date);

    // Найти события до указанной даты
    List<EventEntity> findByDateStartBefore(LocalDate date);

    // Найти события по точному времени начала
    List<EventEntity> findByDateStartAndTimeStart(LocalDate dateStart, LocalTime timeStart);

    // Найти события в конкретный день с временным диапазоном
    @Query("SELECT e FROM EventEntity e WHERE e.dateStart = :date " +
            "AND e.timeStart >= :startTime AND e.timeEnd <= :endTime")
    List<EventEntity> findEventsByDateAndTimeRange(
            @Param("date") LocalDate date,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime);

    // Найти события, которые пересекаются с указанным временным интервалом
    @Query("SELECT e FROM EventEntity e WHERE e.dateStart = :date " +
            "AND NOT (e.timeEnd <= :startTime OR e.timeStart >= :endTime)")
    List<EventEntity> findOverlappingEvents(
            @Param("date") LocalDate date,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime);

    // Найти события по названию (с поиском без учета регистра)
    List<EventEntity> findByTitleContainingIgnoreCase(String titlePart);

    // Найти события по точному времени окончания
    List<EventEntity> findByTimeEnd(LocalTime timeEnd);

    // Найти события, начинающиеся в определенный час
    @Query("SELECT e FROM EventEntity e WHERE FUNCTION('HOUR', e.timeStart) = :hour")
    List<EventEntity> findByStartHour(@Param("hour") int hour);
}