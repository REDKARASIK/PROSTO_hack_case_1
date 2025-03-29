package com.example.demo.controller;

import com.example.demo.dto.EventRequest;
import com.example.demo.dto.EventResponse;
import com.example.demo.service.EventService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events-info")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<EventResponse> createEvent(@Valid @RequestBody EventRequest request) {
        EventResponse response = eventService.createEvent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<EventResponse>> getAllEvents() {
        List<EventResponse> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> getEventById(@PathVariable Long id) {
        EventResponse event = eventService.getEventById(id);
        return ResponseEntity.ok(event);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventResponse> updateEvent(
            @PathVariable Long id,
            @Valid @RequestBody EventRequest request) {
        EventResponse response = eventService.updateEvent(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

//    // Дополнительные endpoints для работы с датами и временем
//    @GetMapping("/between-dates")
//    public ResponseEntity<List<EventResponse>> getEventsBetweenDates(
//            @RequestParam("startDate") String startDate,
//            @RequestParam("endDate") String endDate) {
//        List<EventResponse> events = eventService.getEventsBetweenDates(startDate, endDate);
//        return ResponseEntity.ok(events);
//    }
//
//    @GetMapping("/upcoming")
//    public ResponseEntity<List<EventResponse>> getUpcomingEvents(
//            @RequestParam(value = "days", defaultValue = "7") int days) {
//        List<EventResponse> events = eventService.getUpcomingEvents(days);
//        return ResponseEntity.ok(events);
//    }
}