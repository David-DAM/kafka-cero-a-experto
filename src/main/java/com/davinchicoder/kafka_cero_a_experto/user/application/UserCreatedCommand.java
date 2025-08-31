package com.davinchicoder.kafka_cero_a_experto.user.application;

import com.davinchicoder.kafka_cero_a_experto.common.application.Command;
import com.davinchicoder.kafka_cero_a_experto.common.application.VoidResponse;
import com.davinchicoder.kafka_cero_a_experto.user.domain.Role;
import lombok.Data;

import java.time.Instant;

@Data
public class UserCreatedCommand implements Command<VoidResponse> {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private Role role;
    private Instant timestamp;

}
