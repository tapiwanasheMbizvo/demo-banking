package com.tapiwanashe.mbizvo.banking.demo.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/hello")
public class HelloWorld {



    record  Greeting(String greeting , LocalDateTime time){}

    @GetMapping
    public ResponseEntity<Greeting> getGreeting(){
        var greeting = new Greeting("Hello there", LocalDateTime.now());
        return  ResponseEntity.ok(greeting);
    }
}
