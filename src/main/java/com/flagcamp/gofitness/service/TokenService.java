package com.flagcamp.gofitness.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {

    public static final String TOKEN_SECRET = "s4T2zOIWHMM1sxq";

    public String createToken(String userEmail, String role) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            String token = JWT.create()
                    .withClaim("userEmail", userEmail)
                    .withClaim("role", role)
                    .withClaim("createdAt", new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis() + (1000 * 60 * 60)))
                    .sign(algorithm);
            return token;
        } catch (UnsupportedEncodingException exception) {
            exception.printStackTrace();
            //log WRONG Encoding message
        } catch (JWTCreationException exception) {
            exception.printStackTrace();
            //log Token Signing Failed
        }
        return null;
    }

    public Map<String, String> getUserInfoFromToken(String token) {
        Map<String, String> map = new HashMap<>();
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            map.put("userEmail", jwt.getClaim("userEmail").asString());
            map.put("role", jwt.getClaim("role").asString());
            return map;
        } catch (UnsupportedEncodingException exception) {
            exception.printStackTrace();
            //log WRONG Encoding message
            return null;
        } catch (JWTVerificationException exception) {
            exception.printStackTrace();
            //log Token Verification Failed
            return null;
        }
    }

    public boolean isTokenValid(String token) {
        Map<String, String> userInfo = this.getUserInfoFromToken(token);
        return userInfo.get("userEmail") != null;
    }
}
