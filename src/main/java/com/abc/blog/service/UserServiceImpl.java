package com.abc.blog.service;

import com.abc.blog.model.AppUser;
import com.abc.blog.model.AuthenticationRequest;
import com.abc.blog.model.AuthenticationResponse;
import com.abc.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.abc.blog.model.Role.USER;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final JwtService jwtService;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse createAccount(AppUser appUser) {
        appUser.setCreatedAt(new Date());
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUser.setRole(appUser.getRole());
        userRepository.save(appUser);
        String token = jwtService.generateJwtTokenWithUserDetailsOnly(appUser);
        return AuthenticationResponse.builder()
                .accessToken(token)
                .build();
    }

    public AuthenticationResponse login (AuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AppUser appUser = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new UsernameNotFoundException("Username not found"));
        String token = jwtService.generateJwtTokenWithUserDetailsOnly(appUser);
        return AuthenticationResponse.builder()
                .accessToken(token)
                .build();
    }
}
