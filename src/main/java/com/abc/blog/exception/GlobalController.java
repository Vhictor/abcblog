package com.abc.blog.exception;

import com.abc.blog.model.ErrorResponseDataFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalController extends ResponseEntityExceptionHandler {


    @ExceptionHandler({GlobalRequestException.class})
    public ResponseEntity<Object> handleDataNotFoundException (GlobalRequestException exception){
        ErrorResponseDataFormat responseDataFormat = new ErrorResponseDataFormat(
                false,exception.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(responseDataFormat, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorResponseDataFormat responseDataFormat = new ErrorResponseDataFormat(
                false,ex.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(responseDataFormat, HttpStatus.BAD_REQUEST);
    }
}
