package com.example.demo.service;

import com.example.demo.dto.EventRequest;
import com.example.demo.dto.EventResponse;
import com.example.demo.entity.EventEntity;
import com.example.demo.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    @Transactional
    public EventResponse createEvent(EventRequest request) {
        // Проверка, что время окончания не раньше времени начала
        LocalDateTime startDateTime = LocalDateTime.of(request.getDateStart(), request.getTimeStart());
        LocalDateTime endDateTime = LocalDateTime.of(request.getDateEnd(), request.getTimeEnd());

        if (endDateTime.isBefore(startDateTime)) {
            throw new IllegalArgumentException("Время окончания не может быть раньше времени начала");
        }

        EventEntity event = new EventEntity();
        event.setTitle(request.getTitle());
        event.setDateStart(request.getDateStart());
        event.setTimeStart(request.getTimeStart());
        event.setDateEnd(request.getDateEnd());
        event.setTimeEnd(request.getTimeEnd());

        EventEntity savedEvent = eventRepository.save(event);
        return convertToDto(savedEvent);
    }

    @Transactional(readOnly = true)
    public List<EventResponse> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public EventResponse getEventById(Long id) {
        EventEntity event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Событие не найдено"));
        return convertToDto(event);
    }

    @Transactional
    public EventResponse updateEvent(Long id, EventRequest request) {
        EventEntity event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Событие не найдено"));

        event.setTitle(request.getTitle());
        event.setDateStart(request.getDateStart());
        event.setTimeStart(request.getTimeStart());
        event.setDateEnd(request.getDateEnd());
        event.setTimeEnd(request.getTimeEnd());

        EventEntity updatedEvent = eventRepository.save(event);
        return convertToDto(updatedEvent);
    }

    @Transactional
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    private EventResponse convertToDto(EventEntity entity) {
        EventResponse dto = new EventResponse();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDateStart(entity.getDateStart());
        dto.setTimeStart(entity.getTimeStart());
        dto.setDateEnd(entity.getDateEnd());
        dto.setTimeEnd(entity.getTimeEnd());
        return dto;
    }
}