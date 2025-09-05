package com.davinchicoder.kafka_cero_a_experto.user.application.emailUpdated;

import com.davinchicoder.kafka_cero_a_experto.common.application.Command;
import com.davinchicoder.kafka_cero_a_experto.common.application.VoidResponse;
import lombok.Data;

import java.time.Instant;

@Data
public class UserEmailUpdatedCommand implements Command<VoidResponse> {

    private Long id;
    private String email;
    private Instant timestamp;

}
