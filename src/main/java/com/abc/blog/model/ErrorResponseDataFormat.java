package com.abc.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ErrorResponseDataFormat {

    private boolean success;
    private String message;
    private HttpStatus status;
}
