package com.pl.github.api.gitapp.userdata;

import com.pl.github.api.gitapp.userdata.dto.UserDataView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static com.pl.github.api.gitapp.userdata.TestUserDataFactory.RESPONSE_ID;
import static com.pl.github.api.gitapp.userdata.TestUserDataFactory.USER_DATA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserDataServiceImplTest {

    @Mock
    UserRepository repository;

    @Mock
    CalculationsService calculations;

    @Mock
    RestTemplate restTemplate;

    @Mock
    UserDataMapper mapper;

    UserDataServiceImpl service;

    @BeforeEach
    void init() {
        service = new UserDataServiceImpl(
                repository, calculations, restTemplate, mapper);
    }

    @Disabled
    @Test
    void shouldGetUserData() {
        // given
        given(repository.findByLogin("master"))
                .willReturn(Optional.ofNullable(USER_DATA));

        // when
        UserDataView actualUserDataView = service.getUserData("Bartlomiej21");

        // then
        assertEquals(RESPONSE_ID, actualUserDataView.getId());
    }
}
