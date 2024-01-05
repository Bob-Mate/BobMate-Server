package com.umc.bobmate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BobMateApplication {

    public static void main(String[] args) {
        SpringApplication.run(BobMateApplication.class, args);
    }

}
