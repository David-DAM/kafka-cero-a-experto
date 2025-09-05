package com.davinchicoder.kafka_cero_a_experto.user.application.deleted;

import com.davinchicoder.kafka_cero_a_experto.common.application.Command;
import com.davinchicoder.kafka_cero_a_experto.common.application.VoidResponse;
import lombok.Data;

import java.time.Instant;

@Data
public class UserDeletedCommand implements Command<VoidResponse> {

    private Long id;
    private Instant timestamp;

}
