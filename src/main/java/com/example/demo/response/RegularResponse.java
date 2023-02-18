package com.example.demo.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.ResponseBody;

@Setter
@Getter
@AllArgsConstructor
public class RegularResponse {
    private String messageType;
    private Object data;
    private String message;
}
