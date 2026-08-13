package com.bank.bankingtransfersystem.application.service;

import com.bank.bankingtransfersystem.domain.User;
import com.bank.bankingtransfersystem.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserJoinService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User join(String email, String rawPassword, String name) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("이미 가입된 이메일 입니다.");
        }

        String encodedPassword = passwordEncoder.encode(rawPassword);
        User user = User.create(email, encodedPassword, name);

        return userRepository.save(user);
    }
}
