package com.pl.github.api.gitapp.userdata;

import com.pl.github.api.gitapp.userdata.dto.GitHubResponse;
import com.pl.github.api.gitapp.userdata.dto.UserDataView;
import org.springframework.stereotype.Component;

@Component
public class UserDataMapper {

    UserDataEntity toEntity(GitHubResponse response) {
        return new UserDataEntity(response.getId(),
                response.getLogin(), 0);
    }

    UserDataView toView(GitHubResponse response, double calculations) {
        return new UserDataView(String.valueOf(response.getId()),
                response.getLogin(),
                response.getName(),
                response.getType(),
                response.getAvatarUrl(),
                response.getCreatedAt(),
                calculations);
    }
}
