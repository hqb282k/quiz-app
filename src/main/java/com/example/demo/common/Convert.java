package com.example.demo.common;

import org.springframework.stereotype.Component;
@Component
public class Convert {
    public String bearerTokenToToken(String token){
        if (token.startsWith("Bearer")) {
            token = token.substring(7);
        }
        return token;
    }
}
