package com.pl.github.api.gitapp.userdata;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.pl.github.api.gitapp.userdata.dto.UserDataView;
import com.pl.github.api.gitapp.utils.TestPostgresContainer;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.pl.github.api.gitapp.userdata.TestUserDataFactory.GITHUB_ENDPOINT;
import static com.pl.github.api.gitapp.userdata.TestUserDataFactory.USER_DATA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Sql(scripts = "/reset-and-insert-data.sql")
class UserDataIntegrationTest extends TestPostgresContainer {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @SneakyThrows
    void shouldGetUserData() {
        // given
        String gitHubURL = GITHUB_ENDPOINT;

        // when
        MvcResult actual =
                mockMvc
                        .perform(MockMvcRequestBuilders.get(gitHubURL))
                        .andExpect(status().isOk())
                        .andReturn();

        UserDataView userDataResult =
                objectMapper.readValue(actual.getResponse().getContentAsString(), UserDataView.class);

        // then
        assertEquals(USER_DATA.getId(), Long.parseLong(userDataResult.getId()));
        assertEquals(USER_DATA.getLogin(), userDataResult.getLogin());
        assertEquals(USER_DATA.getRequestCount(), 1);
    }

}
