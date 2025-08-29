package com.davinchicoder.kafka_cero_a_experto.common.infrastructure.event.util;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.reflect.ReflectData;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;

@Service
public class MessagingUtil {

    public Message<SpecificRecord> buildMessage(SpecificRecord specificRecord, MessageHeaders headers) {
        return MessageBuilder
                .withPayload(specificRecord)
                .copyHeaders(headers)
                .build();
    }

    public SpecificRecord getSpecificRecord(GenericRecord genericRecord) throws NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        Schema schema = genericRecord.getSchema();
        Class<? extends SpecificRecord> recordClass = ReflectData.get().getClass(schema);
        return recordClass.getConstructor().newInstance();
    }

}
