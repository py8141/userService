package com.shopkart.userService.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtils {
    public static String generateJwtToken(String username, SecretKey secretKey, long expirationMillis) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationMillis);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public static boolean validateJwtToken(String token, SecretKey secretKey) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true; // Token is valid
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Token is invalid
        }
    }

//    public static Claims getClaimsFromToken(String token, SecretKey secretKey) {
//        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
//    }
}
