package com.pl.github.api.gitapp.userdata;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Entity
@Builder
@Getter
@Setter
public class UserDataEntity {

    @Id
    private Long id;

    private String login;
    private int requestCount;

    public void incrementRequestCount() {
        requestCount++;
    }
}
