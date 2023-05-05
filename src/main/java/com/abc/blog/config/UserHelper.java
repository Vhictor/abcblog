package com.abc.blog.config;

import com.abc.blog.exception.GlobalRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserHelper {
    public static String getUserEmail() {
        try {
            return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception ex) {
            throw new GlobalRequestException("Unauthorized Access");
        }
    }
}
