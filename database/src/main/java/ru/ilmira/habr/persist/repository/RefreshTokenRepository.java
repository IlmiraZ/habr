package ru.ilmira.habr.persist.repository;

import ru.ilmira.habr.persist.model.RefreshToken;
import ru.ilmira.habr.persist.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    Page<RefreshToken> findAllByUser(Pageable pageable, User user);

    @Modifying
    void deleteByUser(User user);
}
