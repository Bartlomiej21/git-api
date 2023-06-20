package com.pl.github.api.gitapp.userdata.exception;

public class CorruptedGitHubResponseException extends RuntimeException {
    public CorruptedGitHubResponseException() {
        super("Unable to get proper response");
    }
}
