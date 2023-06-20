package com.pl.github.api.gitapp.userdata;

import com.pl.github.api.gitapp.userdata.dto.GitHubResponse;


class TestUserDataFactory {

    static final String LOGIN = "bartlomiej21";
    static final String GITHUB_URI = "https://api.github.com/users/" + LOGIN;
    static final Long ID = 75323382L;
    static final String ID_STRING = "75323382";
    static final int REQUEST_COUNT = 1;
    static final String NAME = null;
    static final String TYPE = "User";
    static final String AVATAR_URL = "https://avatars.githubusercontent.com/u/75323382?v=4";
    static final String CREATED_AT = "2020-12-01T15:19:56Z";
    static final String PUBLIC_REPOS = "6";
    static final String FOLLOWERS = "0";
    static final String GITHUB_LOGIN = "Bartlomiej21";
    static final String URI_TEMPLATE = "/users/{login}";
    static final String INVALID_LOGIN = "jsfdungfd743";


    static final UserDataEntity USER_DATA =
            UserDataEntity.builder()
                    .id(ID)
                    .login(LOGIN)
                    .requestCount(REQUEST_COUNT)
                    .build();

    static final GitHubResponse GITHUB_RESPONSE =
            GitHubResponse.builder()
                    .id(ID)
                    .login(LOGIN)
                    .name(NAME)
                    .type(TYPE)
                    .avatarUrl(AVATAR_URL)
                    .createdAt(CREATED_AT)
                    .publicRepos(PUBLIC_REPOS)
                    .followers(FOLLOWERS)
                    .build();
}
