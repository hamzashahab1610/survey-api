package com.example.restapi.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDetailsCommandLineRunner implements CommandLineRunner {
    private Logger logger=LoggerFactory.getLogger(getClass());
    private UserDetailsRepository repository;

    public UserDetailsCommandLineRunner(UserDetailsRepository repository) {
        super();
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        repository.save(new UserDetails("Hamza", "Admin"));
        repository.save(new UserDetails("Zaid", "Admin"));
        repository.save(new UserDetails("Ibrahim", "User"));


        List<UserDetails> users = repository.findByRole("Admin");

        users.forEach(userDetails -> logger.info(userDetails.toString()));

    }
}
