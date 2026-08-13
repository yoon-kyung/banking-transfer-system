package com.bank.bankingtransfersystem.domain;

import com.github.f4b6a3.ulid.UlidCreator;
import lombok.Getter;

import java.time.Instant;

@Getter
public class User {
    private final String id;
    private final String email;
    private final String password;
    private final String name;
    private final Instant createdAt;

    private User(String id, String email, String password, String name, Instant createdAt) {
        validateEmail(email);
        validatePassword(password);
        validateName(name);

        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.createdAt = createdAt;
    }

    private static void validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("email은 필수입니다.");
            // TODO email 형식 검증
        }
    }

    private static void validatePassword(String password) {
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("password는 필수입니다.");
            // TODO password 형식 검증
        }
    }

    private static void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name은 필수입니다.");
        }
    }

    public static User create(String email, String encodedPassword, String name) {
        return new User(UlidCreator.getMonotonicUlid().toString(), email, encodedPassword, name, Instant.now());
    }

    // DB에서 조회한 값 domain으로 mapping
    public static User of(String id, String email, String password, String name, Instant createdAt) {
        return new User(id, email, password, name, createdAt);
    }
}
