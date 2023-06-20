package com.pl.github.api.gitapp.userdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDataEntity, Long> {
    Optional<UserDataEntity> findByLogin(String login);
}
