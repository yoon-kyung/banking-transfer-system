package com.bank.bankingtransfersystem.adapter.out.persistence;

import com.bank.bankingtransfersystem.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserJpaEntity {

    @Id
    @Column(length = 50, updatable = false, nullable = false)
    private String id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    private UserJpaEntity(String id, String email, String password, String name, Instant createdAt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.createdAt = createdAt;
    }

    public static UserJpaEntity fromDomain(User user) {
        return new UserJpaEntity(user.getId(), user.getEmail(), user.getPassword(), user.getName(), user.getCreatedAt());
    }

    public User toDomain() {
        return User.of(id, email, password, name, createdAt);
    }
}
