package com.Group10.Project02;

import com.Group10.Project02.Entities.Users;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
public class UsersController {
    private final UsersRepository repository;

    UsersController(UsersRepository repository){
        this.repository = repository;
    }

    @GetMapping("/users")
    List<Users> all(){
        return repository.findAll();
    }

    @PostMapping("/users")
    Users newUser(@RequestBody Users newUsers){
        return repository.save(newUsers);
    }

    @GetMapping("/users/{id}")
    EntityModel<Users> one(@PathVariable Long id){
        Users users = repository.findById(id)
                .orElseThrow( );

        return EntityModel.of(users,
                linkTo(methodOn(UsersController.class).one(id)).withSelfRel(),
                linkTo(methodOn(UsersController.class).all()).withRel("users"));
    }
    @GetMapping(value="/users", params = "username")
    List<Users> getUsers(@RequestParam("username") String username){
        return repository.findByUsername(username);
    }

    @PutMapping("/users/{id}")
    Users replaceUser(@RequestBody Users newUsers, @PathVariable Long id){
        return repository.findById(id).map(users -> {
                users.setUsername(newUsers.getUsername());
                return repository.save(users);
            }).orElseGet(() -> {
            return repository.save(newUsers);
        });
    }
}
