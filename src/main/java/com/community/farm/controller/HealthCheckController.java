package com.community.farm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class HealthCheckController {
    @GetMapping()
    String helloWorld() {
        return "Hello World from Community Farm";
    }
}