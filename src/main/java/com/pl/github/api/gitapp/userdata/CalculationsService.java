package com.pl.github.api.gitapp.userdata;

import com.pl.github.api.gitapp.userdata.dto.GitHubResponse;
import org.springframework.stereotype.Service;

@Service
public class CalculationsService {

    double calculateByFollowersAndPublicRepos(GitHubResponse response) {
        String followers = response.getFollowers();
        String publicRepos = response.getPublicRepos();
        double numberOfFollowers = Double.parseDouble(followers);
        double numberOfPublicRepos = Double.parseDouble(publicRepos);
        double divider = numberOfFollowers * (2 + numberOfPublicRepos);
        if (divider == 0) {
            return 0;
        }
        return 6 / divider;
    }
}
