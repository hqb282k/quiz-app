package com.example.demo.service;
import com.example.demo.model.dto.UpdateUserDto;
import com.example.demo.model.dto.UserChangePassDto;
import com.example.demo.model.dto.UserSignInDto;
import com.example.demo.model.dto.UserSignUpDto;
import com.example.demo.response.RegularResponse;
import com.example.demo.response.SignInResponse;


import org.springframework.http.ResponseEntity;

public interface UserService {
    public ResponseEntity<SignInResponse> login(UserSignInDto userSignInDto);

    public ResponseEntity<RegularResponse> register(UserSignUpDto userSignUpDto);

    public ResponseEntity<RegularResponse> changePassword(UserChangePassDto userChangePassDto);

    public ResponseEntity<RegularResponse> delete(String username);

    public ResponseEntity<RegularResponse> update(String username, UpdateUserDto updateUserDto);
}
