package com.swp391.QuizSytem.util;

public class AuthenticationUtil {
    public static String bearerTokenToToken(String token){
        if (token.startsWith("Bearer")) {
            token = token.substring(7);
        }
        return token;
    }
}
