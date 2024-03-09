package com.anwhiteko.vk.rest.proxy;

import com.anwhiteko.vk.processing.db.UserRepository;
import com.anwhiteko.vk.rest.auth.jwt.configuration.JwtTokenConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
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
    private final JwtTokenConfig config;

    @GetMapping
    public String test() {
        return Encoders.BASE64.encode(Jwts.SIG.HS256.key().build().getEncoded());
    }

    @GetMapping("/hidden")
    public String hidden() {
        return "Boo!";
    }
}
