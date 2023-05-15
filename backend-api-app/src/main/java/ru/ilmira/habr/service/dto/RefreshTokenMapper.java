package ru.ilmira.habr.service.dto;

import ru.ilmira.habr.persist.model.RefreshToken;

public interface RefreshTokenMapper {

    RefreshTokenDto fromRefreshToken(RefreshToken refreshToken);
}
