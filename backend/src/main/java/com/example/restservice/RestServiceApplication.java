package com.example.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;


@SpringBootApplication
@RestController
public class RestServiceApplication {

    @Autowired
    ItemService service;

    public static void main(String[] args) {
        SpringApplication.run(RestServiceApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello(
            @RequestParam(value = "name", defaultValue = "World") String name,
            @RequestParam(value = "adj", defaultValue = "Cool") String adj

    ) {
        return String.format("Hello %s %s!", adj, name);
    }
}
