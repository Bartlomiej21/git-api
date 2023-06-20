package com.pl.github.api.gitapp.userdata.exception;

public class GitHubEmptyResponseException extends RuntimeException {
    public GitHubEmptyResponseException() {
        super("Did not receive response from GitHub");
    }
}
