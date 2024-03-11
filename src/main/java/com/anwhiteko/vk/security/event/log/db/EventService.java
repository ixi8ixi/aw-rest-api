package com.anwhiteko.vk.security.event.log.db;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public void saveEvent(EventEntity event) {
        eventRepository.save(event);
    }
}
