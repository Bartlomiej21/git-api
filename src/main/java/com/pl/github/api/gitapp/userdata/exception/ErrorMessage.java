package com.pl.github.api.gitapp.userdata.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ErrorMessage {

    int httpStatus;
    String message;
}
