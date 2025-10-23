package com.iagobc.cursoSpringBoot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Greeting2Controller {

    @GetMapping({"/greeting/{name}", "/salute/{name}", "/hello/{name}"})
    public String greeting (@PathVariable String name) {
        return "Hello " + name + ", i am working.";
    }
}
