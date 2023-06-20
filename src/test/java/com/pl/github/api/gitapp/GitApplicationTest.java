package com.pl.github.api.gitapp;

import com.pl.github.api.gitapp.utils.TestPostgresContainer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class GitApplicationTest extends TestPostgresContainer {

    @Test
    void contextLoads() {
    }
}
