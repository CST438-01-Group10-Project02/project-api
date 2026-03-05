package com.Group10.Project02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.Group10.Project02.Entities.Event;
import com.Group10.Project02.Entities.Users;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UsersRepository usersRepository, EventRepository eventRepository) {
        return args -> {
            log.info("Preloading " + usersRepository.save(new Users("TestUser1")));
            log.info("Preloading " + eventRepository.save(new Event("Super fun Party", usersRepository.findById(1L).orElseThrow(), "TestEvent", "Description", "12:00", "18:00", "2026-01-01")));

        };
    }
}
