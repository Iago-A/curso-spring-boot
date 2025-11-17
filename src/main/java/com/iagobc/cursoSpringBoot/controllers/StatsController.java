package com.iagobc.cursoSpringBoot.controllers;

import com.iagobc.cursoSpringBoot.domain.Stat;
import com.iagobc.cursoSpringBoot.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/stats")
public class StatsController {
    @Autowired
    private StatsService statsService;

    @GetMapping
    public ResponseEntity<Stat> getStats () {
        Stat stat = statsService.getStats();

        //Response 200
        return ResponseEntity.ok(stat);
    }
}
