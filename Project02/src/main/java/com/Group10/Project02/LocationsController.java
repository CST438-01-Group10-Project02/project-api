package com.Group10.Project02;

import com.Group10.Project02.Entities.Locations;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Peter Gloag
 * @since 2/25/2026
 */

@RestController
public class LocationsController {
    private final LocationsRepository repository;

    LocationsController(LocationsRepository repository){
        this.repository = repository;
    }

    @GetMapping("/locations")
    List<Locations> all(){
        return repository.findAll();
    }

    @PostMapping("/locations")
    Locations newLocation(@RequestBody Locations newLocation){
        return repository.save(newLocation);
    }

    @GetMapping("/locations/{id}")
    EntityModel<Locations> one(@PathVariable Long id){
        Locations location = repository.findById(id)
                .orElseThrow();

        return EntityModel.of(location,
                linkTo(methodOn(LocationsController.class).one(id)).withSelfRel(),
                linkTo(methodOn(LocationsController.class).all()).withRel("locations"));
    }

    @PutMapping("/locations/{id}")
    Locations replaceLocation(@RequestBody Locations newLocation, @PathVariable Long id){
        return repository.findById(id).map(location -> {
            location.setName(newLocation.getName());
            return repository.save(location);
        }).orElseGet(() -> {
            return repository.save(newLocation);
        });
    }
}
