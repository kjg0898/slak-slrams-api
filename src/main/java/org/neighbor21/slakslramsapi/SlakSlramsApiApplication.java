package org.neighbor21.slakslramsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SlakSlramsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SlakSlramsApiApplication.class, args);
    }

}
