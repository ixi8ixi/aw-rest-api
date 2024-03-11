package com.anwhiteko.vk.security.event.log.db;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE) // todo remove access
@Getter
@Setter
public class EventEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "event_time")
    LocalDateTime eventTime;
    @Column(name = "username")
    String username;
    @Column(name = "method")
    String method;
    @Column(name = "code")
    Integer code;
    @Column(name = "uri")
    String uri;
}
