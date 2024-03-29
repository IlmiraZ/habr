package ru.ilmira.habr.service.dto;

import ru.ilmira.habr.persist.model.RefreshToken;
import org.springframework.stereotype.Component;

@Component
public class RefreshTokenMapperImpl implements RefreshTokenMapper {

    @Override
    public RefreshTokenDto fromRefreshToken(RefreshToken refreshToken) {
        return RefreshTokenDto.builder()
                .id(refreshToken.getId())
                .userId(refreshToken.getUser().getId())
                .username(refreshToken.getUser().getUsername())
                .token(refreshToken.getToken())
                .expiryDate(refreshToken.getExpiryDate())
                .created(refreshToken.getCreated())
                .build();
    }
}
