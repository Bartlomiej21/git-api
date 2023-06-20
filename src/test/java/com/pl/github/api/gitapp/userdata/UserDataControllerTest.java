package com.pl.github.api.gitapp.userdata;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pl.github.api.gitapp.utils.TestPostgresContainer;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;

import static com.pl.github.api.gitapp.userdata.TestUserDataFactory.INVALID_LOGIN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@WebMvcTest(controllers = UserDataController.class)
@AutoConfigureMockMvc
@SpringBootTest
public class UserDataControllerTest extends TestPostgresContainer {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mvc;

    @SneakyThrows
    @Test
    void whenValidGetThenReturns200() {
        mvc.perform(get("/users/{login}", "master")).andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    void whenInvalidGetThenReturns404() {
        final var login = INVALID_LOGIN;
        mvc.perform(get("/users/{login}", login)).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("User with login " + login + " not found"))
                .andExpect(jsonPath("$.httpStatus").value(404));
    }

    @SneakyThrows
    @Test
    void shouldAddNewUserData() {
        mvc.perform(
                get("/users/{login}", "Bartlomiej21")).andExpect(status().isOk());
//                .andExpect(jsonPath("$.id").value(75323382L))
//                .andExpect(jsonPath("$.login").value("Bartlomiej21"))
//                .andExpect(jsonPath("$.avatarUrl").value("https://avatars.githubusercontent.com/u/75323382?v=4"))
//                .andExpect(jsonPath("$.type").value("User"));
//                 .andExpect(jsonPath("$.login").value("Bartlomiej21"))
//                 .andExpect(jsonPath("$.login").value("Bartlomiej21"))
//                 .andExpect(jsonPath("$.login").value("Bartlomiej21"))

        final var after = jdbcTemplate.queryForObject("SELECT request_count FROM users WHERE login = 'Bartlomiej21'", Integer.class);
        Assertions.assertEquals(1, after);
    }


}
