package com.davinchicoder.kafka_cero_a_experto.user.application.created;

import com.davinchicoder.kafka_cero_a_experto.common.application.CommandHandler;
import com.davinchicoder.kafka_cero_a_experto.common.application.VoidResponse;
import com.davinchicoder.kafka_cero_a_experto.user.domain.entity.User;
import com.davinchicoder.kafka_cero_a_experto.user.domain.port.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserCreatedHandler implements CommandHandler<UserCreatedCommand, VoidResponse> {

    private final UserRepository userRepository;

    @Override
    public VoidResponse handle(UserCreatedCommand command) {
        log.info("UserCreatedHandler command: {}", command);

        User user = User.builder()
                .id(command.getId())
                .firstname(command.getFirstname())
                .lastname(command.getLastname())
                .email(command.getEmail())
                .role(command.getRole())
                .password(RandomStringUtils.randomAlphanumeric(10))
                .createdAt(command.getTimestamp())
                .updatedAt(null)
                .deletedAt(null)
                .build();

        userRepository.save(user);

        return new VoidResponse();
    }

    @Override
    public Class<UserCreatedCommand> getCommandType() {
        return UserCreatedCommand.class;
    }
}
