package com.sekpiseth.exception;

import com.sekpiseth.base.BaseError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiException {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ResponseStatusException.class)
    public BaseError<?> handlerServiceException(ResponseStatusException e){
        return BaseError.builder()
                .status(false)
                .code(e.getStatusCode().value())
                .timestamp(LocalDateTime.now())
                .message("Something Went Wrong Please Check...!")
                .errors(e.getReason())
                .build();
    }

}
