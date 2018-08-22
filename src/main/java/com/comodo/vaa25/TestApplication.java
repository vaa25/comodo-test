package com.comodo.vaa25;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Clock;

@SpringBootApplication
public class TestApplication {

    public static void main(final String... args) {
        SpringApplication.run(TestApplication.class, args);
    }

    @Bean
    public Clock clock(){
        return Clock.systemDefaultZone();
    }

}
