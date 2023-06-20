package com.pl.github.api.gitapp.userdata;

import com.pl.github.api.gitapp.utils.TestPostgresContainer;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@WebMvcTest(controllers = UserDataController.class)
@AutoConfigureMockMvc
@SpringBootTest
public class UserDataControllerTest extends TestPostgresContainer {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

//    @Autowired private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mvc;

//    @Autowired
//    private TestEntityManager entityManager;

//    @MockBean private UserDataServiceImpl service;

    @SneakyThrows
    @Test
    void whenValidGetThenReturns200() {
        mvc.perform(get("/users/{login}", "master")).andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    void whenInvalidGetThenReturns404() {
        mvc.perform(get("/users/{login}", "jsfdungfd743")).andExpect(status().isNotFound());
    }


}
