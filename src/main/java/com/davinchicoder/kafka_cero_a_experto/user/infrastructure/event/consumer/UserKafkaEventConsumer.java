package com.davinchicoder.kafka_cero_a_experto.user.infrastructure.event.consumer;

import com.davinchicoder.kafka_cero_a_experto.common.infrastructure.event.consumer.EventSpecificConsumer;
import com.davinchicoder.kafka_cero_a_experto.common.infrastructure.event.util.MessagingUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Component
@Slf4j
public class UserKafkaEventConsumer implements Consumer<Message<GenericRecord>> {

    private Map<String, EventSpecificConsumer<SpecificRecord>> eventSpecificConsumerMap;

    private MessagingUtil messagingUtil;

    public UserKafkaEventConsumer(List<EventSpecificConsumer> specificConsumers, MessagingUtil messagingUtil) {
        eventSpecificConsumerMap = specificConsumers.stream().collect(
                Collectors.toMap(
                        EventSpecificConsumer::getSchema,
                        consumer -> consumer
                )
        );
        this.messagingUtil = messagingUtil;
    }


    @KafkaListener(topics = "${app.kafka.topics.user}", groupId = "${app.kafka.group-id}", containerFactory = "kafkaListenerContainerFactory")
    @Override
    public void accept(Message<GenericRecord> genericRecordMessage) {

        log.info("Kafka Event message: {}", genericRecordMessage.toString());

        SpecificRecord specificRecord;

        try {
            specificRecord = messagingUtil.getSpecificRecord(genericRecordMessage.getPayload());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String schemaFullName = specificRecord.getClass().getTypeName();

        EventSpecificConsumer<SpecificRecord> specificConsumer = eventSpecificConsumerMap.get(schemaFullName);

        if (specificConsumer != null) {
            log.info("Found consumer for schema: {}", schemaFullName);
            Message<SpecificRecord> specificRecordMessage = messagingUtil.buildMessage(
                    (SpecificRecord) genericRecordMessage.getPayload(), genericRecordMessage.getHeaders()
            );

            specificConsumer.accept(specificRecordMessage);
        } else {
            log.warn("No consumer found for schema: {}", schemaFullName);
        }

    }
}
