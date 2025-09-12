package com.davinchicoder.kafka_cero_a_experto.IT.helper;

import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class KafkaHelper {

    @Value("${app.kafka.topics.user.validation}")
    private String validationTopic;

    @Autowired
    private KafkaConsumer<String, GenericRecord> userValidationConsumer;

    public void refreshTopics() {
        this.cleanAllRecords();
        this.suscribeToConsumerTopics();
    }

    private void suscribeToConsumerTopics() {
        userValidationConsumer.subscribe(List.of(validationTopic));
    }

    private void cleanAllRecords() {
        this.suscribeToConsumerTopics();
        this.cleanTopics(userValidationConsumer);

    }

    private void cleanTopics(KafkaConsumer<String, GenericRecord>... consumer) {
        Arrays.stream(consumer).forEach(KafkaHelper::cleanConsumer);
    }

    private static void cleanConsumer(KafkaConsumer<String, GenericRecord> consumer) {
        try {
            while (true) {

                ConsumerRecords<String, GenericRecord> records = consumer.poll(Duration.ofSeconds(1L));
                if (records.count() == 0) {
                    break;
                }
                consumer.commitSync();
            }
        } catch (WakeupException e) {
            log.info("Cleaning consumer due to wakeup exception");
        } catch (Exception e) {
            log.error("Error while cleaning consumer: {}", e.getMessage());
        } finally {
            try {
                consumer.commitSync();
            } finally {
                consumer.unsubscribe();
            }
        }
    }

    public ConsumerRecords<String, GenericRecord> getUserValidationRecords() {
        return KafkaTestUtils.getRecords(userValidationConsumer, Duration.ofSeconds(5L));
    }
}
