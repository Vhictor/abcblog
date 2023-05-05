package com.abc.blog.model;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String email;
    private String password;
}
