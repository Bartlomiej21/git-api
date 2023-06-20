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
//
//    @Disabled
//    @Test
//    @SneakyThrows
//    void shouldSaveNewUserDataEntity() {
//        // todo actual returns UserDataView so it does not work
//        // given
//        String gitHubURL = GITHUB_ENDPOINT_2;
//
//        // when
//        MvcResult actual =
//                mockMvc
//                        .perform(MockMvcRequestBuilders.get(gitHubURL))
//                        .andExpect(status().isOk())
//                        .andReturn();
//
//        GitHubResponse gitHubResponseResult =
//                objectMapper.readValue(actual.getResponse().getContentAsString(), GitHubResponse.class);
//
//        // then
//        assertEquals(GITHUB_RESPONSE.getPublicRepos(), gitHubResponseResult.getPublicRepos());
//        assertEquals(GITHUB_RESPONSE.getFollowers(), gitHubResponseResult.getFollowers());
//    }


//    @Test
//    @SneakyThrows
//    void shouldUpdateVisit() {
//        // given
//        String visitURL = VISIT_ENDPOINT;
//
//        // when
//        MvcResult actual =
//                mockMvc
//                        .perform(
//                                MockMvcRequestBuilders.put(visitURL)
//                                        .contentType(MediaType.APPLICATION_JSON)
//                                        .content(JSON_UPDATE))
//                        .andExpect(status().isOk())
//                        .andReturn();
//
//        VisitDTOView visitResult =
//                objectMapper.readValue(actual.getResponse().getContentAsString(), VisitDTOView.class);
//
//        // then
//        assertEquals(UPDATED_MEDICAL_ID, visitResult.medicalServiceId());
//    }

}
