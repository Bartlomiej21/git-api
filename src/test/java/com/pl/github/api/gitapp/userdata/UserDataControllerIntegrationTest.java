package com.pl.github.api.gitapp.userdata;

import com.pl.github.api.gitapp.utils.TestPostgresContainer;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static com.pl.github.api.gitapp.userdata.TestUserDataFactory.AVATAR_URL;
import static com.pl.github.api.gitapp.userdata.TestUserDataFactory.CREATED_AT;
import static com.pl.github.api.gitapp.userdata.TestUserDataFactory.GITHUB_LOGIN;
import static com.pl.github.api.gitapp.userdata.TestUserDataFactory.ID;
import static com.pl.github.api.gitapp.userdata.TestUserDataFactory.INVALID_LOGIN;
import static com.pl.github.api.gitapp.userdata.TestUserDataFactory.TYPE;
import static com.pl.github.api.gitapp.userdata.TestUserDataFactory.URI_TEMPLATE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Sql(scripts = "/reset-and-insert-data.sql")
class UserDataControllerIntegrationTest extends TestPostgresContainer {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    void shouldGetUserData() {
        // given
        String login = GITHUB_LOGIN;

        // when
        mockMvc.perform(get(URI_TEMPLATE, login))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.login").value(login))
                .andExpect(jsonPath("$.type").value(TYPE))
                .andExpect(jsonPath("$.avatarUrl").value(AVATAR_URL))
                .andExpect(jsonPath("$.createdAt").value(CREATED_AT))
                .andExpect(jsonPath("$.calculations").value(0.0));
    }

    @SneakyThrows
    @Test
    void whenValidRequestThenReturns200() {
        // given
        String login = "master";
        // when
        mockMvc.perform(get(URI_TEMPLATE, login))
                // then
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    void whenInvalidRequestThenReturns404() {
        // given
        final var login = INVALID_LOGIN;
        // when
        mockMvc.perform(get(URI_TEMPLATE, login)).andExpect(status().isNotFound())
                //then
                .andExpect(jsonPath("$.message").value("User with login " + login + " not found"))
                .andExpect(jsonPath("$.httpStatus").value(404));
    }

    @SneakyThrows
    @Test
    void shouldAddNewUserData() {
        // given
        String login = GITHUB_LOGIN;
        // when
        mockMvc.perform(get(URI_TEMPLATE, login))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.login").value(login));

        final var requestCount = jdbcTemplate
                .queryForObject("SELECT request_count FROM users WHERE login = 'bartlomiej21'", Integer.class);
        Assertions.assertEquals(1, requestCount);
    }

    @SneakyThrows
    @Test
    void shouldIncrementUserDataRequestCountIfUserDataPresent() {
        // given
        String login = "master";
        final var countBefore = jdbcTemplate
                .queryForObject("SELECT request_count FROM users WHERE login = 'master'", Integer.class);
        //when
        mockMvc.perform(get(URI_TEMPLATE, login)).andExpect(status().isOk())
                // then
                .andExpect(jsonPath("$.id").value(7073L))
                .andExpect(jsonPath("$.login").value(login));

        final var countAfter = jdbcTemplate.
                queryForObject("SELECT request_count FROM users WHERE login = 'master'", Integer.class);
        Assertions.assertEquals(countBefore + 1, countAfter);
    }
}
