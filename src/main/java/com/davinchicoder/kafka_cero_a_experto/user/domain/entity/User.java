package com.davinchicoder.kafka_cero_a_experto.user.domain.entity;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class User {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Role role;
    Instant createdAt;
    Instant updatedAt;
    Instant deletedAt;

}
