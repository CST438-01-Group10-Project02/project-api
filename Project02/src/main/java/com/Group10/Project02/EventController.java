package com.Group10.Project02;

import com.Group10.Project02.Entities.Event;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
class EventController {
    private final EventRepository repository;

    EventController(EventRepository repository) {
        this.repository = repository;
    }

    //get all event data
    @PostMapping("/Events")
    Event newEvent(@RequestBody Event newEvent) {
        return repository.save(newEvent);
    }

    @GetMapping("/events")
    List<Event> all() {
        return repository.findAll();
    }

    // Create queries for searching events

    // Single params
    @GetMapping(value="/events", params = "location")
    List<Event> getEventsLoc(@RequestParam("location") String location){
        return repository.findByLocation(location);
    }

    @GetMapping(value="/events", params = "hostId")
    List<Event> getEventsHId(@RequestParam("hostId") Long id){
        return repository.findByHostId(id);
    }

    @GetMapping(value="/events", params = "date")
    List<Event> getEventsDate(@RequestParam("date") String date){
        return repository.findByDate(date);
    }

    // Double params
    @GetMapping(value="/events", params = {"location","hostId"})
    List<Event> getEventsLocHId(@RequestParam("location") String location, @RequestParam("hostId") Long hostId){
        return repository.findByLocationHostId(location, hostId);
    }

    @GetMapping(value="/events", params = {"location","date"})
    List<Event> getEventsLocDate(@RequestParam("location") String location, @RequestParam("date") String date){
        return repository.findByLocationDate(location, date);
    }

    @GetMapping(value="/events", params = {"hostId","date"})
    List<Event> getEventsLocHId(@RequestParam("hostId") Long hostId, @RequestParam("date") String date){
        return repository.findByHostIdDate(hostId, date);
    }

    // Triple params
    @GetMapping(value="/events", params = {"location","hostId","date"})
    List<Event> getEventsLocHIdDate(@RequestParam("location") String location, @RequestParam("hostId") Long hostId, @RequestParam("date") String date){
        return repository.findByLocationHostIdDate(location, hostId, date);
    }


    // Get event info from user id
    @GetMapping("/events/{id}")
    EntityModel<Event> getEvent(@PathVariable Long id) {
        Event event = repository.findById(id)
                .orElseThrow();

        return EntityModel.of(event,
                linkTo(methodOn(EventController.class).getEvent(id)).withSelfRel(),
                linkTo(methodOn(EventController.class).all()).withRel("events")
        );
    }

    @PutMapping("/events/{id}")
    Event replaceEvent(@PathVariable Long id, @RequestBody Event newEvent) {
        return repository.findById(id).map(event -> {
            event.setName(newEvent.getName());
            return repository.save(event);
        }).orElseGet(()->{
            return repository.save(newEvent);
        });
    }

    @DeleteMapping("/event/{id}")
    void deleteEvent(@PathVariable Long id) { repository.deleteById(id);}
}
