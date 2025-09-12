package com.davinchicoder.kafka_cero_a_experto.IT.helper;

import com.davinchicoder.kafka_cero_a_experto.user.domain.entity.User;
import com.davinchicoder.kafka_cero_a_experto.user.domain.port.UserRepository;
import org.awaitility.Durations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

import static org.awaitility.Awaitility.await;

@Service
public class DatabaseHelper {

    @Autowired
    private UserRepository userRepository;

    public User findByIdAndCondition(Long id, Predicate<User> predicate) {

        await()
                .atLeast(Durations.ONE_HUNDRED_MILLISECONDS)
                .atMost(Durations.TEN_SECONDS)
                .with()
                .pollInterval(Durations.TWO_HUNDRED_MILLISECONDS)
                .ignoreException(Exception.class)
                .until(() -> userRepository.findById(id).isPresent() &&
                        predicate.test(userRepository.findById(id).get())
                );

        return userRepository.findById(id).orElseThrow();

    }

}
