package ru.ilmira.habr.auth;

import ru.ilmira.habr.payload.request.LoginRequest;
import ru.ilmira.habr.payload.request.SignupRequest;
import ru.ilmira.habr.payload.request.TokenRefreshRequest;
import ru.ilmira.habr.payload.response.JwtResponse;
import ru.ilmira.habr.payload.response.TokenRefreshResponse;
import ru.ilmira.habr.persist.model.User;
import ru.ilmira.habr.util.token.RefreshTokenCreator;
import ru.ilmira.habr.util.user.UserCreator;

import java.util.List;

public class AuthCreator {

    public static final String USERNAME = "shuricans";
    public static final String FIRSTNAME = "Sasha";
    public static final String PASSWORD = "password";
    public static final String TOKEN = "token-test";
    public static final String TYPE = "Bearer";
    public static final String ROLE_USER = "ROLE_USER";

    public static final User USER = UserCreator.createUser();

    public static LoginRequest createLoginRequest() {
        return LoginRequest
                .builder()
                .username(USERNAME)
                .password(PASSWORD)
                .build();
    }

    public static SignupRequest createSignupRequest() {
        return SignupRequest
                .builder()
                .username(USERNAME)
                .password(PASSWORD)
                .firstName(FIRSTNAME)
                .build();
    }

    public static JwtResponse createJwtResponse() {
        return JwtResponse.builder()
                .token(TOKEN)
                .type(TYPE)
                .refreshToken(RefreshTokenCreator.TOKEN)
                .username(USER.getUsername())
                .authorities(List.of(ROLE_USER))
                .build();
    }

    public static TokenRefreshRequest createTokenRefreshRequest() {
        return TokenRefreshRequest.builder()
                .refreshToken(RefreshTokenCreator.TOKEN)
                .build();
    }

    public static TokenRefreshResponse createTokenRefreshResponse() {
        return TokenRefreshResponse.builder()
                .accessToken(TOKEN)
                .refreshToken(RefreshTokenCreator.TOKEN)
                .tokenType(TYPE)
                .build();
    }

}
