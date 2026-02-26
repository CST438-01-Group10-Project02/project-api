package com.Group10.Project02;

import com.Group10.Project02.Entities.Locations;
import com.Group10.Project02.Entities.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.stream.Location;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UsersRepository usersRepository, LocationsRepository locationsRepository){
        return args -> {
            log.info("Preloading " + usersRepository.save(new Users("TestUser1")));
            log.info("Preloading " + locationsRepository.save(new Locations("My House")));
        };
    }

}
