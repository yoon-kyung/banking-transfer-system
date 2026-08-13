package com.bank.bankingtransfersystem.adapter.in.web.dto;

import com.bank.bankingtransfersystem.domain.User;

import java.time.Instant;

public record UserJoinResponse(String id, String email, String name, Instant createdAt) {
    public static UserJoinResponse from(User user) {
        return new UserJoinResponse(user.getId(), user.getEmail(), user.getEmail(), user.getCreatedAt());
    }
}
