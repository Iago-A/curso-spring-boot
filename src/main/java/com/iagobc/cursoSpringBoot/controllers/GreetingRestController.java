package com.iagobc.cursoSpringBoot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingRestController {

    @GetMapping({"/greeting", "/salute", "/hello"})
    public String Greeting () {
        return "Hello, i am working";
    }
}
