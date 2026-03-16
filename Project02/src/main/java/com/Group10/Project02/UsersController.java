package com.Group10.Project02;

import java.util.List;

import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Group10.Project02.Entities.Users;

@RestController
public class UsersController {

    private final UsersRepository repository;

    UsersController(UsersRepository repository) {
        this.repository = repository;
    }

    @CrossOrigin
    @GetMapping("/users")
    List<Users> all() {
        return repository.findAll();
    }

    @CrossOrigin
    @PostMapping("/users")
    Users newUser(@RequestBody Users newUsers) {
        return repository.save(newUsers);
    }

    @CrossOrigin
    @GetMapping("/users/{id}")
    EntityModel<Users> one(@PathVariable Long id) {
        Users users = repository.findById(id)
                .orElseThrow();

        return EntityModel.of(users,
                linkTo(methodOn(UsersController.class).one(id)).withSelfRel(),
                linkTo(methodOn(UsersController.class).all()).withRel("users"));
    }

    @CrossOrigin
    @GetMapping(value = "/users", params = "username")
    List<Users> getUsers(@RequestParam("username") String username) {
        return repository.findByUsername(username);
    }

    @CrossOrigin
    @PutMapping("/users/{id}")
    Users replaceUser(@RequestBody Users newUsers, @PathVariable Long id) {
        return repository.findById(id).map(users -> {
            users.setUsername(newUsers.getUsername());
            return repository.save(users);
        }).orElseGet(() -> {
            return repository.save(newUsers);
        });
    }
}
