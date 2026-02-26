package com.daclink.users;

import com.daclink.users.Entities.User;
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
    List<User> all(){
        return repository.findAll();
    }

    @PostMapping("/users")
    User newUser(@RequestBody User newUser){
        return repository.save(newUser);
    }

    @GetMapping("/users/{id}")
    EntityModel<User> one(@PathVariable Long id){
        User user = repository.findById(id)
                .orElseThrow( );

        return EntityModel.of(user,
                linkTo(methodOn(UsersController.class).one(id)).withSelfRel(),
                linkTo(methodOn(UsersController.class).all()).withRel("users"));
    }

    @PutMapping("/users/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Long id){
        return repository.findById(id).map(user -> {
                user.setUsername(newUser.getUsername());
                return repository.save(user);
            }).orElseGet(() -> {
            return repository.save(newUser);
        });
    }
}
