package com.pl.github.api.gitapp.userdata;

import com.pl.github.api.gitapp.userdata.dto.GitHubResponse;
import com.pl.github.api.gitapp.userdata.dto.UserDataView;


class TestUserDataFactory {
    static final Long ID = 7073L;
    static final String LOGIN = "master";

    static final String INVALID_LOGIN = "jsfdungfd743";

    static final int REQUEST_COUNT = 1;

    static final Long RESPONSE_ID = 75323382L;
    static final String RESPONSE_ID_STRING = "75323382";
    static final String RESPONSE_LOGIN = "Bartlomiej21";
    static final String NAME = null;
    static final String TYPE = "user";
    static final String AVATAR_URL = "https://avatars.githubusercontent.com/u/75323382?v=4";
    static final String CREATED_AT = "2020-12-01T15:19:56Z";
    static final String PUBLIC_REPOS = "6";
    static final String FOLLOWERS = "0";

    static final UserDataEntity USER_DATA =
            UserDataEntity.builder()
                    .id(ID)
                    .login(LOGIN)
                    .requestCount(REQUEST_COUNT)
                    .build();

    static final GitHubResponse GITHUB_RESPONSE =
            GitHubResponse.builder()
                    .id(RESPONSE_ID)
                    .login(RESPONSE_LOGIN)
                    .name(NAME)
                    .type(TYPE)
                    .avatarUrl(AVATAR_URL)
                    .createdAt(CREATED_AT)
                    .publicRepos(PUBLIC_REPOS)
                    .followers(FOLLOWERS)
                    .build();

    static final UserDataView USER_DATA_VIEW = null; //todo usunąć


    static final String GITHUB_ENDPOINT = "/users/master";
    static final String GITHUB_ENDPOINT_2 = "/users/Bartlomiej21";

    static final String GOOD_RESPONSE =
            """
                    {
                      "login": "Bartlomiej21",
                      "id": 75323382,
                      "node_id": "MDQ6VXNlcjc1MzIzMzgy",
                      "avatar_url": "https://avatars.githubusercontent.com/u/75323382?v=4",
                      "gravatar_id": "",
                      "url": "https://api.github.com/users/Bartlomiej21",
                      "html_url": "https://github.com/Bartlomiej21",
                      "followers_url": "https://api.github.com/users/Bartlomiej21/followers",
                      "following_url": "https://api.github.com/users/Bartlomiej21/following{/other_user}",
                      "gists_url": "https://api.github.com/users/Bartlomiej21/gists{/gist_id}",
                      "starred_url": "https://api.github.com/users/Bartlomiej21/starred{/owner}{/repo}",
                      "subscriptions_url": "https://api.github.com/users/Bartlomiej21/subscriptions",
                      "organizations_url": "https://api.github.com/users/Bartlomiej21/orgs",
                      "repos_url": "https://api.github.com/users/Bartlomiej21/repos",
                      "events_url": "https://api.github.com/users/Bartlomiej21/events{/privacy}",
                      "received_events_url": "https://api.github.com/users/Bartlomiej21/received_events",
                      "type": "User",
                      "site_admin": false,
                      "name": null,
                      "company": null,
                      "blog": "",
                      "location": null,
                      "email": null,
                      "hireable": null,
                      "bio": null,
                      "twitter_username": null,
                      "public_repos": 6,
                      "public_gists": 0,
                      "followers": 0,
                      "following": 0,
                      "created_at": "2020-12-01T15:19:56Z",
                      "updated_at": "2023-05-25T05:37:36Z"
                    }
                    """;

    static final String BAD_RESPONSE =
            """
                    {
                      "login": "Bartlomiej21",
                    }
                    """;
}
