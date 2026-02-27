package com.Group10.Project02;

import com.Group10.Project02.Entities.Users;
import com.Group10.Project02.Entities.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UsersRepository usersRepository, EventRepository eventRepository) {
        return args -> {
            log.info("Preloading " + usersRepository.save(new Users("TestUser1")));
            log.info("Preloading " + eventRepository.save(new Event("Super fun Party", usersRepository.findById(1L).orElseThrow(), "my house", "birthday", "3:30", "5:40")));
            log.info("Preloading " + eventRepository.save(new Event("Come have fun", usersRepository.findById(1L).orElseThrow(), "Test", "You'll enjoy it", "now", "forever")));

        };
    }
}
