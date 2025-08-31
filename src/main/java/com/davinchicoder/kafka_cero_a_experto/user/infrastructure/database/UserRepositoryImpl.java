package com.davinchicoder.kafka_cero_a_experto.user.infrastructure.database;

import com.davinchicoder.kafka_cero_a_experto.user.domain.entity.User;
import com.davinchicoder.kafka_cero_a_experto.user.domain.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final List<UserEntity> userEntityList = new ArrayList<>();

    private final UserEntityMapper userEntityMapper;

    @Override
    public User save(User user) {
        UserEntity userEntity = userEntityMapper.mapToUserEntity(user);
        userEntityList.add(userEntity);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userEntityList.stream()
                .filter(userEntity -> userEntity.getId().equals(id))
                .findFirst()
                .map(userEntityMapper::mapToUser);
    }

    @Override
    public void deleteById(Long id) {
        userEntityList.removeIf(userEntity -> userEntity.getId().equals(id));
    }

    @Override
    public boolean existsByEmail(String email) {
        return userEntityList.stream()
                .anyMatch(userEntity -> userEntity.getEmail().equals(email));
    }
}
