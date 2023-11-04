package com.Brilloconnetz.jwt;

import com.Brilloconnetz.model.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

public class JWTUtil {

    private final Key key;

    public JWTUtil() {
        key = Keys.hmacShaKeyFor(System.getenv("JWT_SECRET_KEY").getBytes());
    }
    public String generateJWT(User user) {

        long expirationMillis = 3600000;

        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("username", user.getUsername())
                .claim("dob", user.getDob())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(key)
                .compact();
    }

    public String verifyJWT(String jwt) {

        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parse(jwt);

            return "verification pass";
        } catch (Exception e) {
            return "verification fails";
        }
    }
}
