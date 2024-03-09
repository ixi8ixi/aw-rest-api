package com.anwhiteko.vk.configuration;

import com.anwhiteko.vk.processing.db.model.Permission;
import com.anwhiteko.vk.rest.auth.jwt.JwtConfigurer;
import com.anwhiteko.vk.rest.auth.jwt.configuration.JwtTokenConfig;
import com.anwhiteko.vk.security.SecurityUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableMethodSecurity
@EnableConfigurationProperties({ JwtTokenConfig.class })
@RequiredArgsConstructor
public class SecurityConfiguration {
    protected final JwtConfigurer jwtConfigurer;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(new AntPathRequestMatcher("/api"))
                            .permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/hidden"))
                            .hasAuthority(Permission.EDIT_ALBUMS.name()) // todo cringe
                        .requestMatchers(new AntPathRequestMatcher("/api/auth/login"))
                            .permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/auth/logout"))
                            .permitAll()
                        .anyRequest().authenticated())
                .apply(jwtConfigurer);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
