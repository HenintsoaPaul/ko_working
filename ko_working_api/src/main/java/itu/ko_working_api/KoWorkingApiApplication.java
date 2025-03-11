package itu.ko_working_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class KoWorkingApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KoWorkingApiApplication.class, args);
    }

}
