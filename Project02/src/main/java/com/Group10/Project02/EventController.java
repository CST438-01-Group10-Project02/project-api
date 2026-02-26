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

    @PostMapping("/Events")
    Event newEvent(@RequestBody Event newEvent) {
        return repository.save(newEvent);
    }

    @GetMapping("/events")
    List<Event> all() {
        return repository.findAll();
    }

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
