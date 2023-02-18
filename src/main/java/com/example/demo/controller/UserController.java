package com.example.demo.controller;

import com.example.demo.model.dto.UpdateUserDto;
import com.example.demo.model.dto.UserSignInDto;
import com.example.demo.model.dto.UserSignUpDto;
import com.example.demo.response.RegularResponse;
import com.example.demo.response.SignInResponse;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import com.swp391.QuizSytem.util.AuthenticationUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/auth/register")
    public ResponseEntity<RegularResponse> register(@RequestBody UserSignUpDto userSignUpDto) {
        return userService.register(userSignUpDto);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<SignInResponse> login(@RequestBody UserSignInDto userSignInDto) {
        return userService.login(userSignInDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/delete/{userName}")
    public ResponseEntity<?> delete(@RequestHeader("Authorization") String token, @PathVariable String username) {
        token = AuthenticationUtil.bearerTokenToToken(token);
        if (!jwtTokenProvider.extractUsername(token).equals(username)) {
            return ResponseEntity.badRequest().body(new RegularResponse("Error", null, "Deny the request"));
        }
        return userService.delete(username);
    }

    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('CLIENT')")
    @PutMapping("/update/{username}")
    public ResponseEntity<?> update(@RequestHeader("Authorization") String token, @PathVariable String username, @RequestBody UpdateUserDto updateUserDto) {
        token = AuthenticationUtil.bearerTokenToToken(token);
        if (!jwtTokenProvider.extractUsername(token).equals(username)) {
            return ResponseEntity.badRequest().body(new RegularResponse("Error", null, "Deny the request"));
        }
        return userService.update(username, updateUserDto);
    }
}
