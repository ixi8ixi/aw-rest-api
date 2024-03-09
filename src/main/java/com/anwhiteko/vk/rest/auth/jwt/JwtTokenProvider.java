package com.anwhiteko.vk.rest.auth.jwt;

import com.anwhiteko.vk.rest.auth.jwt.configuration.JwtTokenConfig;
import com.anwhiteko.vk.rest.auth.jwt.exception.JwtAuthException;
import com.anwhiteko.vk.security.SecurityUserDetailsService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private final String secret;
    private final String header;
    private final long validityTimeSeconds;
    private final SecurityUserDetailsService securityUserDetailsService;

    public JwtTokenProvider(JwtTokenConfig jwtTokenConfig, SecurityUserDetailsService securityUserDetailsService) {
        this.secret = jwtTokenConfig.key();
        this.header = jwtTokenConfig.header();
        this.validityTimeSeconds = jwtTokenConfig.validityTimeSeconds();
        this.securityUserDetailsService = securityUserDetailsService;
    }

    public String createToken(String username, String role) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityTimeSeconds * 1000);
        Claims claims = Jwts.claims().subject(username).add("role", role).build();

        return Jwts.builder()
                .claims(claims)
                .issuedAt(now)
                .expiration(validity)
                .signWith(getSigningKey())
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = parseClaims(token);
            return !claims.getExpiration().before(new Date());
        } catch (IllegalArgumentException | JwtException e) {
            throw new JwtAuthException("Jwt unauthorized :(", HttpStatus.UNAUTHORIZED);
        }

    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = securityUserDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader(header);
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private String getUsername(String token) {
        Claims claims = parseClaims(token);
        return claims.getSubject();
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
