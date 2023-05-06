package com.abc.blog.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    USER_READ("user:read"),

    AUTHOR_READ("author:read"),
    AUTHOR_UPDATE("author:update"),
    AUTHOR_CREATE("author:create"),
    AUTHOR_DELETE("author:delete");

    @Getter
    private final String permission;
}
