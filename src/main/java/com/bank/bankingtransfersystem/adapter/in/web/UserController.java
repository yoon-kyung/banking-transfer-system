package com.bank.bankingtransfersystem.adapter.in.web;

import com.bank.bankingtransfersystem.adapter.in.web.dto.UserJoinRequest;
import com.bank.bankingtransfersystem.adapter.in.web.dto.UserJoinResponse;
import com.bank.bankingtransfersystem.application.service.UserJoinService;
import com.bank.bankingtransfersystem.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {
    private final UserJoinService userJoinService;

    @PostMapping("/join")
    public ResponseEntity<UserJoinResponse> join(@RequestBody UserJoinRequest request) {
        User user = userJoinService.join(request.email(), request.password(), request.name());
        return ResponseEntity.status(HttpStatus.CREATED).body(UserJoinResponse.from(user));
    }
}
