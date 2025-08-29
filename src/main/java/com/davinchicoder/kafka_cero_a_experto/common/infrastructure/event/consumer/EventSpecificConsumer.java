package com.davinchicoder.kafka_cero_a_experto.common.infrastructure.event.consumer;

import org.apache.avro.specific.SpecificRecord;
import org.springframework.messaging.Message;

public interface EventSpecificConsumer<T extends SpecificRecord> {

    void accept(Message<T> genericRecordMessage);

    String getSchema();

}
