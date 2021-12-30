package com.example.biedaolx_api.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Slf4j
@PropertySource("classpath:application.properties")
public class AuthorizationFilter extends OncePerRequestFilter {

    @Value("${jwt.secret}")
    private String secret;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if(request.getServletPath().equals("/login")){
            filterChain.doFilter(request,response);
        }else {

            String token = null;
            String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            Cookie[] cookies = request.getCookies();
            List<Cookie> authCookie = new ArrayList<>();
            if(cookies!=null){
                Arrays.stream(cookies).filter(cookie -> cookie.getName().equals("Token")).forEach(authCookie::add);
            }


            if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
                 token = authorizationHeader.substring("Bearer ".length());
            }else if (authCookie.size()>0){
                token=authCookie.get(0).getValue();

            }else{
                filterChain.doFilter(request, response);
                return;
            }
                try {
                    Algorithm algorithm = Algorithm
                            .HMAC256("secret:2134jkl23j4lh124kh213h4kjh24kjh1234kjhj1234bbsjksadfiu123429sf%&%&*".getBytes(StandardCharsets.UTF_8));
                    JWTVerifier verifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = verifier.verify(token);
                    String username = decodedJWT.getSubject();
                    String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    Arrays.stream(roles).forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));


                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(username,null,authorities);

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request,response);
                }catch (Exception e){
                    log.error("Token error: {}", e.getMessage());
                    response.setHeader("error", e.getMessage());
                    //response.sendError(HttpServletResponse.SC_FORBIDDEN);

                    Map<String,String> error = new HashMap<>();
                    error.put("error", e.getMessage());


                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), error);

                }


        }
    }
}
