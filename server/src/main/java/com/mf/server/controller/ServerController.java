package com.mf.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerController {
    @GetMapping("/test")
    public String test() {
        return "hello";
    }
}
