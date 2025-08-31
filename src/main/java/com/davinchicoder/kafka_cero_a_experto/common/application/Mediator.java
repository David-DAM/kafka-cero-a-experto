package com.davinchicoder.kafka_cero_a_experto.common.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Slf4j
public class Mediator {

    private final Map<Class<?>, CommandHandler<?, ?>> handlers;

    public Mediator(List<CommandHandler<?, ?>> handlerList) {
        handlers = handlerList.stream().collect(Collectors.toMap(CommandHandler::getCommandType, Function.identity()));
    }

    public <R, T extends Command<R>> R dispatch(T command) {
        CommandHandler<T, R> handler = (CommandHandler<T, R>) handlers.get(command.getClass());
        if (handler == null) {
            throw new RuntimeException("No handler found for request type: " + command.getClass());
        }
        return handler.handle(command);
    }


}
