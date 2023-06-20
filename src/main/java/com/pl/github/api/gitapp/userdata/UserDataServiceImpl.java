package com.pl.github.api.gitapp.userdata;

import com.pl.github.api.gitapp.userdata.dto.GitHubResponse;
import com.pl.github.api.gitapp.userdata.dto.UserDataView;
import com.pl.github.api.gitapp.userdata.exception.CorruptedGitHubResponseException;
import com.pl.github.api.gitapp.userdata.exception.GitHubEmptyResponseException;
import com.pl.github.api.gitapp.userdata.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDataServiceImpl implements UserDataService {

    private static final String API_URI = "https://api.github.com/users/{login}";

    private final UserRepository repository;

    private final CalculationsService calculations;

    private final RestTemplate restTemplate;

    private final UserDataMapper mapper;

    @Transactional
    public UserDataView getUserData(String login) {
        return Optional.ofNullable(login).map(log -> {
            final var uri = createApiUri(log);
            final var response = createResponse(uri, log);
//            final var entity = getEntity(log, response);
            final var entity = repository.findByLogin(login).orElse(repository.save(mapper.toEntity(response)));
            // TODO this might actually work if I add ignoreCase somewhere
//            final var entity = repository.findByLogin(login).orElseThrow(()-> new UserNotFoundException(login));

//            final Optional<UserDataEntity> optionalEntity = repository.findByLogin(login);
//            UserDataEntity entity;
//            if (optionalEntity.isPresent()) {
//                entity = optionalEntity.get();
//            } else {
//                entity = mapper.toEntity(response);
//                entity = repository.save(entity);
//            }
            UserDataServiceImpl.log.info("Retrieving entity by login {}", entity.getLogin());
            entity.incrementRequestCount();
            final var calculationResult = calculations.calculateByFollowersAndPublicRepos(response);

            return Optional.ofNullable(response)
                    .map(body -> mapper.toView(response, calculationResult))
                    .orElseThrow(GitHubEmptyResponseException::new);
        }).orElseThrow(() -> new IllegalArgumentException("Login cannot be null"));
    }

    private String createApiUri(String login) {
        return UriComponentsBuilder
                .fromUriString(API_URI)
                .uriVariables(Map.of("login", login))
                .build()
                .toUriString();
    }

    private GitHubResponse createResponse(String uri, String login) {
//        RestTemplate restTemplate = new RestTemplate();
        try {
            final var githubResponse = restTemplate.exchange(uri, GET, null, new ParameterizedTypeReference<GitHubResponse>() {
            });

            log.info("Get response for uri: {}", uri);
            return Optional.ofNullable(githubResponse).map(response -> {
                        final var body = response.getBody();
                        log.info("Get response for uri: {}", body);
                        return body;
                    })
                    .orElseThrow(CorruptedGitHubResponseException::new);

        } catch (HttpClientErrorException e) {
            if (NOT_FOUND == e.getStatusCode()) {
                throw new UserNotFoundException(login);
            }
            throw new CorruptedGitHubResponseException();
        }
    }

    private UserDataEntity getEntity(String login, GitHubResponse response) {
        final Optional<UserDataEntity> optionalEntity = repository.findByLogin(login);
        UserDataEntity entity;
        if (optionalEntity.isPresent()) {
            entity = optionalEntity.get();
        } else {
            entity = mapper.toEntity(response);
            entity = repository.save(entity);
        }
        return entity;
    }
}
