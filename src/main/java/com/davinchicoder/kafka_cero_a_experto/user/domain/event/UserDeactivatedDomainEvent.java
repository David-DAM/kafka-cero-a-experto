package com.davinchicoder.kafka_cero_a_experto.user.domain.event;

import com.davinchicoder.kafka_cero_a_experto.user.domain.entity.User;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class UserDeactivatedDomainEvent {

    private Long id;

    private Long timestamp;

    public static UserDeactivatedDomainEvent of(User user) {
        return UserDeactivatedDomainEvent.builder()
                .id(user.getId())
                .timestamp(Instant.now().toEpochMilli())
                .build();
    }

}
