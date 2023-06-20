package com.pl.github.api.gitapp.userdata.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;


@Value
@AllArgsConstructor
@Builder
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubResponse {

    @JsonProperty("id")
    Long id;

    @JsonProperty(value = "login")
    String login;

    @JsonProperty("name")
    String name;

    @JsonProperty("type")
    String type;

    @JsonProperty("avatar_url")
    String avatarUrl;

    @JsonProperty("created_at")
    String createdAt;

    @JsonProperty("public_repos")
    String publicRepos;

    @JsonProperty("followers")
    String followers;
}
