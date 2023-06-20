package com.pl.github.api.gitapp.userdata;

import com.pl.github.api.gitapp.userdata.dto.UserDataView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserDataController {

    private final UserDataServiceImpl githubServiceImpl;

    @GetMapping(value = "/{login}")
    public UserDataView getUserData(@PathVariable String login) {
        return githubServiceImpl.getUserData(login);
    }
}
