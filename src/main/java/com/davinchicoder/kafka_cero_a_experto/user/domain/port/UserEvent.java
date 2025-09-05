package com.davinchicoder.kafka_cero_a_experto.user.domain.port;

import com.davinchicoder.kafka_cero_a_experto.user.domain.event.UserDeactivatedDomainEvent;
import com.davinchicoder.kafka_cero_a_experto.user.domain.event.UserVerificationRequestedDomainEvent;

public interface UserEvent {

    void sendUserDeactivatedDomainEvent(UserDeactivatedDomainEvent event);

    void sendUserVerificationRequestedDomainEvent(UserVerificationRequestedDomainEvent event);

}
