package com.example.biedaolx_api.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Getter
@PropertySource("classpath:application.properties")
public class JwtService {

    private final String secret;

    public JwtService( @Value("${jwt.secret}")String secret) {
        this.secret = secret;
    }

    public String getUsernameFromToken(HttpServletRequest request){
        String token = "";

        if(request.getHeader(HttpHeaders.AUTHORIZATION) != null
                && request.getHeader(HttpHeaders.AUTHORIZATION).startsWith("Bearer ")){
            token = getTokenFromHeader(request);
        }else {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                List<Cookie> authToken = Arrays.stream(cookies).filter(cookie -> cookie.getName().equals("Token")).collect(Collectors.toList());
                if (authToken.size() > 0) {
                    token = authToken.get(0).getValue();
                }
            }
        }

        Algorithm algorithm = Algorithm
                .HMAC256(secret.getBytes(StandardCharsets.UTF_8));
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getSubject();
    }

    public String getTokenFromHeader(HttpServletRequest request){
        return request.getHeader(HttpHeaders.AUTHORIZATION).substring("Bearer ".length());
    }
}
