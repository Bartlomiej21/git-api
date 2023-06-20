package com.pl.github.api.gitapp.userdata;

import com.pl.github.api.gitapp.userdata.dto.GitHubResponse;
import com.pl.github.api.gitapp.userdata.dto.UserDataView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static com.pl.github.api.gitapp.userdata.TestUserDataFactory.GITHUB_RESPONSE;
import static com.pl.github.api.gitapp.userdata.TestUserDataFactory.RESPONSE_ID_STRING;
import static com.pl.github.api.gitapp.userdata.TestUserDataFactory.USER_DATA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserDataServiceImplTest {

    @Mock
    UserRepository repository;

    @Mock
    RestTemplate restTemplate;

    UserDataServiceImpl service;

    @BeforeEach
    void init() {
        service = new UserDataServiceImpl(repository, new CalculationsService(), restTemplate, new UserDataMapper());
    }

    @Test
    void shouldGetUserData() {
        // given
        final var response = new ResponseEntity<GitHubResponse>(GITHUB_RESPONSE, HttpStatus.OK);
        given(restTemplate.exchange("https://api.github.com/users/Bartlomiej21", HttpMethod.GET, null, new ParameterizedTypeReference<GitHubResponse>() {
        })).willReturn(response);

        given(repository.findByLogin("Bartlomiej21"))
                .willReturn(Optional.ofNullable(USER_DATA));


        // when
        UserDataView actualUserDataView = service.getUserData("Bartlomiej21");

        // then
        assertEquals(RESPONSE_ID_STRING, actualUserDataView.getId());
    }
}
