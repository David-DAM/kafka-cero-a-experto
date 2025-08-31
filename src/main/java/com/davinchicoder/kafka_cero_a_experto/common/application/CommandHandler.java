package com.davinchicoder.kafka_cero_a_experto.common.application;

public interface CommandHandler<T extends Command<R>, R> {

    R handle(T command);

    Class<T> getCommandType();

}
