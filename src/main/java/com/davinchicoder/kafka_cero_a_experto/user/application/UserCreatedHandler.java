package com.davinchicoder.kafka_cero_a_experto.user.application;

import com.davinchicoder.kafka_cero_a_experto.common.application.CommandHandler;
import com.davinchicoder.kafka_cero_a_experto.common.application.VoidResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserCreatedHandler implements CommandHandler<UserCreatedCommand, VoidResponse> {

    @Override
    public VoidResponse handle(UserCreatedCommand command) {
        log.info("UserCreatedHandler command: {}", command);

        return new VoidResponse();
    }

    @Override
    public Class<UserCreatedCommand> getCommandType() {
        return UserCreatedCommand.class;
    }
}
