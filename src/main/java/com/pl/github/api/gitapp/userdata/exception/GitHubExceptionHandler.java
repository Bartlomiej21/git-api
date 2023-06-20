package com.pl.github.api.gitapp.userdata.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RestControllerAdvice
public class GitHubExceptionHandler {

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorMessage handleAccessDeniedException(UserNotFoundException ex) {
        log.error(ex.getMessage(), ex);
        return new ErrorMessage(NOT_FOUND.value(), ex.getMessage());
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({CorruptedGitHubResponseException.class, GitHubEmptyResponseException.class})
    public ErrorMessage handleWrongResponseException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ErrorMessage(BAD_REQUEST.value(), ex.getMessage());
    }
}




