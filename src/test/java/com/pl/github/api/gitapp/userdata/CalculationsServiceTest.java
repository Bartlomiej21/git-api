package com.pl.github.api.gitapp.userdata;

import com.pl.github.api.gitapp.userdata.dto.GitHubResponse;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculationsServiceTest {

    final CalculationsService calculationsService = new CalculationsService();

    private static Stream<Object[]> githubResponseProvider() {
        return Stream.of(
                new Object[]{createGitHubResponse(1L, "ada", "Ada", "user",
                        "http://github.com/avatar1.jpg", "2023-01-01", "5", "0"), 0},
                new Object[]{createGitHubResponse(2L, "beata", "Beata", "user",
                        "http://github.com/avatar2.jpg", "2022-12-31", "0", "3"), 1.0},
                new Object[]{createGitHubResponse(3L, "cecylia", "Cecylia", "admin",
                        "http://github.com/avatar3.jpg", "2022-12-31", "22", "1"), 0.25}
        );
    }

    private static GitHubResponse createGitHubResponse(Long id, String login, String name, String type,
                                                       String avatarUrl, String createdAt, String publicRepos,
                                                       String followers) {
        GitHubResponse response = Mockito.mock(GitHubResponse.class);
        Mockito.when(response.getId()).thenReturn(id);
        Mockito.when(response.getLogin()).thenReturn(login);
        Mockito.when(response.getName()).thenReturn(name);
        Mockito.when(response.getType()).thenReturn(type);
        Mockito.when(response.getAvatarUrl()).thenReturn(avatarUrl);
        Mockito.when(response.getCreatedAt()).thenReturn(createdAt);
        Mockito.when(response.getPublicRepos()).thenReturn(publicRepos);
        Mockito.when(response.getFollowers()).thenReturn(followers);
        return response;
    }

    @ParameterizedTest
    @MethodSource("githubResponseProvider")
    void testCalculateByFollowersAndPublicRepos(GitHubResponse response, double expectedValue) {
        double actualValue = calculationsService.calculateByFollowersAndPublicRepos(response);
        assertEquals(expectedValue, actualValue);
    }
}
