package com.pl.github.api.gitapp.userdata.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;


@Value
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class UserDataView {
    String id;
    String login;
    String name;
    String type;
    String avatarUrl;
    String createdAt;
    double calculations;
}
