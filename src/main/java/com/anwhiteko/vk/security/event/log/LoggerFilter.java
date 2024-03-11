package com.anwhiteko.vk.security.event.log;

import com.anwhiteko.vk.security.event.log.db.EventEntity;
import com.anwhiteko.vk.security.event.log.db.EventService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class LoggerFilter extends OncePerRequestFilter {
    private final AuthFacade authFacade;
    private final EventService eventService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            chain.doFilter(request, response);
        } finally {
            String username = null;
            Authentication auth = authFacade.getAuth();
            if (auth != null) {
                username = auth.getName();
            }
            EventEntity event = EventEntity.builder()
                    .eventTime(LocalDateTime.now())
                    .username(username)
                    .method(request.getMethod())
                    .code(response.getStatus())
                    .uri(request.getRequestURI())
                    .build();

            eventService.saveEvent(event);
        }
    }
}
