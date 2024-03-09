package com.anwhiteko.vk.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestProxyController {
    @GetMapping
    public int test() {
        return 777;
    }

    @GetMapping("/hidden")
    public String hidden() {
        return "Boo!";
    }
}
