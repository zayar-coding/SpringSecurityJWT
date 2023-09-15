package com.example.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtils {
    @Value("${jwt_secret}")
    private String secret;

    public String generateToken(String email)throws IllegalArgumentException, JWTCreationException {
        return JWT.create().withSubject("Zayar_Making_JWT_Token").withClaim("email",email).
                withIssuedAt(new Date()).withIssuer("ZAYAR").sign(Algorithm.HMAC256(secret));
    }
    public String validateTokenAndRetrieveSubject(String token)throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("Zayar_Making_JWT_Token")
                .withIssuer("ZAYAR")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("email").asString();

    }
}
