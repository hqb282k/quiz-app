package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;
@Data
public class UpdateUserDto {
    private String firstName;
    private String lastName;
    private Long age;
}
