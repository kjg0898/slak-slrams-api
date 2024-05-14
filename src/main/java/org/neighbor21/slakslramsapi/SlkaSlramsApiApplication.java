package org.neighbor21.slakslramsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SlkaSlramsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SlkaSlramsApiApplication.class, args);
    }

}
