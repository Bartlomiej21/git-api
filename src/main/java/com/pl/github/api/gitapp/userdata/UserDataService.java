package com.pl.github.api.gitapp.userdata;

import com.pl.github.api.gitapp.userdata.dto.UserDataView;

public interface UserDataService {
    UserDataView getUserData(String login);
}
