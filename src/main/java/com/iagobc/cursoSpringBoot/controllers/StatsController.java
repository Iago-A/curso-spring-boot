package com.iagobc.cursoSpringBoot.controllers;


import com.iagobc.cursoSpringBoot.domain.Stat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/stats")
public class StatsController {

    @GetMapping
    public ResponseEntity<Stat> getStats () {
        return null;
    }
}
