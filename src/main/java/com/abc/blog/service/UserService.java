package com.abc.blog.service;

import com.abc.blog.model.AppUser;
import com.abc.blog.model.AuthenticationRequest;
import com.abc.blog.model.AuthenticationResponse;

public interface UserService {

    AuthenticationResponse createAccount(AppUser appUser);

    AuthenticationResponse login(AuthenticationRequest appUser);
}
