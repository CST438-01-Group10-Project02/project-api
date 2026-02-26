package com.Group10.Project02;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class UsersPageController {

    private final UsersRepository repository;

    UsersPageController(UsersRepository repository){
        this.repository = repository;
    }

    @GetMapping("/")
    public String viewHomePage(Model model){
        model.addAttribute("allUsers", repository.findAll());
        return "index";
    }
}
