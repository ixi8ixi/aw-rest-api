package com.anwhiteko.vk.rest.controller;

import com.anwhiteko.vk.processing.db.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RestProxyController {

    private final UserRepository userRepository;



    @GetMapping
    public int test() {
        return 777;
    }

    @GetMapping("/hidden")
    public String hidden() {
        return "Boo!";
    }
}
