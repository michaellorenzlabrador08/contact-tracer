package com.innovage.contacttracer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ContactTracerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContactTracerApplication.class, args);
    }

}
