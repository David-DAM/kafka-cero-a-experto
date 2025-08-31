package com.davinchicoder.kafka_cero_a_experto.user.domain.port;

import com.davinchicoder.kafka_cero_a_experto.user.domain.entity.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(Long id);

    void deleteById(Long id);

    boolean existsByEmail(String email);

}
