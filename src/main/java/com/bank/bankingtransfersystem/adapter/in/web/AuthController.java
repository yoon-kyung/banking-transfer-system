package com.bank.bankingtransfersystem.adapter.in.web;

import com.bank.bankingtransfersystem.adapter.in.web.dto.UserLoginRequest;
import com.bank.bankingtransfersystem.application.service.UserLoginService;
import com.bank.bankingtransfersystem.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserLoginService userLoginService;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest httpServletRequest) {
        User user = userLoginService.login(userLoginRequest.email(), userLoginRequest.password());

        HttpSession session = httpServletRequest.getSession();
        session.setAttribute(SessionConst.LOGIN_USER_ID, user.getId());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok().build();
    }
}
