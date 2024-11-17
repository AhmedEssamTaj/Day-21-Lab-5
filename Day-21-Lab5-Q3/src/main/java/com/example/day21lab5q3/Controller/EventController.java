package com.example.day21lab5q3.Controller;

import com.example.day21lab5q3.ApiResponse.ApiResponse;
import com.example.day21lab5q3.Model.Event;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event")

public class EventController {

    ArrayList<Event> events= new ArrayList<Event>();

    // endpoint to get all the events ...
    @GetMapping("/get/all")
    public ArrayList<Event> getEvents() {
        return events;
    }

    // endpoint to add an event
    @PostMapping("/add")
    public ApiResponse setEvents(@RequestBody Event event) {
        events.add(event);
        return new ApiResponse("Event added successfully");
    }

    // endpoint to update an event
    @PutMapping("/update/{id}")
    public ApiResponse updateEvent(@PathVariable String id , @RequestBody Event event) {

        for (Event e : events) {
            if (e.getId().equals(id)) {
                events.set(events.indexOf(e), event);
                return new ApiResponse("Event updated successfully");
            }
        }
        return new ApiResponse("Event not found");

    }

    // endpoint to delete an event
    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteEvent(@PathVariable String id) {
        for (Event e : events) {
            if (e.getId().equals(id)) {
                events.remove(e);
                return new ApiResponse("Event deleted successfully");
            }
        }
        return new ApiResponse("Event not found");
    }

    // endpoint to change the capacity of an event
    @PutMapping("/update/capacity/{id}/{capacity}")
    public ApiResponse getEventById(@PathVariable int capacity,@PathVariable String id) {
        for (Event e : events) {
            if (e.getId().equals(id)) {
                events.get(events.indexOf(e)).setCapacity(capacity);
                return new ApiResponse("Event capacity updated successfully");
            }
        }
        return new ApiResponse("Event not found");
    }

    // endpoint to search for an event by id
@GetMapping("get/id/{id}")
    public Event getEventById(@PathVariable String id) {
        for (Event e : events) {
            if (e.getId().equals(id)) {
                return e;

            }
        }
        return null;
    }




}
