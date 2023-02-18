package com.example.demo.model.dto;

import lombok.*;

@Data
public class UserChangePassDto {
    private String currentPassword;
    private String newPassword;
}

