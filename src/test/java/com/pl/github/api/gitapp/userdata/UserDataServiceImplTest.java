package com.pl.github.api.gitapp.userdata;

import com.pl.github.api.gitapp.userdata.dto.GitHubResponse;
import com.pl.github.api.gitapp.userdata.dto.UserDataView;
import com.pl.github.api.gitapp.userdata.exception.CorruptedGitHubResponseException;
import com.pl.github.api.gitapp.userdata.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static com.pl.github.api.gitapp.userdata.TestUserDataFactory.GITHUB_RESPONSE;
import static com.pl.github.api.gitapp.userdata.TestUserDataFactory.GITHUB_URI;
import static com.pl.github.api.gitapp.userdata.TestUserDataFactory.ID_STRING;
import static com.pl.github.api.gitapp.userdata.TestUserDataFactory.LOGIN;
import static com.pl.github.api.gitapp.userdata.TestUserDataFactory.USER_DATA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

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
        given(restTemplate.exchange(GITHUB_URI, GET, null, new ParameterizedTypeReference<GitHubResponse>() {
        })).willReturn(response);

        given(repository.findByLogin(LOGIN))
                .willReturn(Optional.ofNullable(USER_DATA));


        // when
        UserDataView actualUserDataView = service.getUserData(LOGIN);

        // then
        assertEquals(ID_STRING, actualUserDataView.getId());
    }

    @Test
    void shouldThrowUserNotFoundExceptionIfNoUser() {
        // given
        final var response = new ResponseEntity<GitHubResponse>(GITHUB_RESPONSE, HttpStatus.OK);
        final var clientErrorException = mock(HttpClientErrorException.class);
        given(clientErrorException.getStatusCode()).willReturn(NOT_FOUND);
        given(restTemplate.exchange(GITHUB_URI, GET, null, new ParameterizedTypeReference<GitHubResponse>() {
        })).willThrow(clientErrorException);

        // when
        final var exception =
                assertThrows(
                        UserNotFoundException.class,
                        () -> service.getUserData(LOGIN));
        // then
        assertThat(exception.getMessage()).isEqualTo("User with login " + LOGIN + " not found");
    }

    @Test
    void shouldThrowCorruptedGitHubResponseExceptionIfInvalidResponse() {
        // given
        final var response = new ResponseEntity<GitHubResponse>(GITHUB_RESPONSE, HttpStatus.OK);
        final var clientErrorException = mock(HttpClientErrorException.class);
        given(clientErrorException.getStatusCode()).willReturn(BAD_REQUEST);
        given(restTemplate.exchange(GITHUB_URI, GET, null, new ParameterizedTypeReference<GitHubResponse>() {
        })).willThrow(clientErrorException);

        // when
        final var exception =
                assertThrows(
                        CorruptedGitHubResponseException.class,
                        () -> service.getUserData(LOGIN));
        // then
        assertThat(exception.getMessage()).isEqualTo("Unable to get proper response");
    }

    @Test
    void shouldThrowGitHubEmptyResponseExceptionIfEmptyResponse() {
        // given
        final ResponseEntity<GitHubResponse> response = null;

        given(restTemplate.exchange(GITHUB_URI, GET, null, new ParameterizedTypeReference<GitHubResponse>() {
        })).willReturn(response);

        // when
        final var exception =
                assertThrows(
                        CorruptedGitHubResponseException.class,
                        () -> service.getUserData(LOGIN));
        // then
        assertThat(exception.getMessage()).isEqualTo("Unable to get proper response");
    }
}
