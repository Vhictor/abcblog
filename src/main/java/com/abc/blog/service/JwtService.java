package com.abc.blog.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface JwtService {

    String extractUsername (String token);

    Claims extractClaims (String token);

    String generateJwtToken (Map<String, Object> extraClaims, UserDetails userDetails);

    boolean isTokenValid (String token, UserDetails userDetails);

    String generateJwtTokenWithUserDetailsOnly (UserDetails userDetails);

    <T> T extractSingleClaim (String token, Function<Claims, T> claimsFunctionResolver);
}
