package com.bank.bankingtransfersystem.adapter.out.persistence;

import com.bank.bankingtransfersystem.domain.User;
import com.bank.bankingtransfersystem.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    @Override
    public User save(User user) {
        UserJpaEntity userJpaEntity = userJpaRepository.save(UserJpaEntity.fromDomain(user));
        return userJpaEntity.toDomain();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email).map(UserJpaEntity::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userJpaRepository.existsByEmail(email);
    }
}