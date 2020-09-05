package com.example.demo;

import com.example.repository.UserRepository;
import com.example.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.domain.User;


@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            userRepository.save(new User("Daniela", "Chetan", "daniela_chetan@yahoo.com",
                    "0740145927", "str. Fabricii nr. 5"));
            userRepository.findAll().forEach(user -> log.info("Preloaded " + user));
        };
    }
}
