package com.Group10.Project02;

import com.Group10.Project02.Entities.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    public static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(EventRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Event("Super fun Party", "Name", "my house", "birthday", "3:30", "5:40")));
        };
    }
}
