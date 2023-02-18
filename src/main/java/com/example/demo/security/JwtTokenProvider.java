package com.example.demo.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Component
@Slf4j
public class JwtTokenProvider {
    private final String SECRET_KEY = "secret";

    //    Get username from token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //    Get expiration from token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    //    Check token is expired or not
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Generate token from user details
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails);
    }

    /*=================================Sub method============================================*/
    public boolean hasClaim(String token, String claimName) {
        final Claims claims = extractAllClaims(token);
        return claims.get(claimName) != null;
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public String generateToken(UserDetails userDetails, Map<String, Object> claims) {
        return createToken(claims, userDetails);
    }

    private String createToken(Map<String, Object> claims, UserDetails userDetail) {
        return Jwts.builder().setClaims(claims).setSubject(userDetail.getUsername()).claim("authorities", userDetail.getAuthorities()).setIssuedAt(new Date(System.currentTimeMillis())).
//                set token have expiration is 24 hours
        setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(24))).signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
