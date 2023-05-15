package ru.ilmira.habr.service;

import ru.ilmira.habr.payload.request.LoginRequest;
import ru.ilmira.habr.payload.request.SignupRequest;
import ru.ilmira.habr.payload.request.TokenRefreshRequest;
import ru.ilmira.habr.payload.response.JwtResponse;
import ru.ilmira.habr.payload.response.MessageResponse;
import ru.ilmira.habr.payload.response.TokenRefreshResponse;

public interface AuthService {

    JwtResponse signIn(LoginRequest loginRequest);

    MessageResponse signUp(SignupRequest signUpRequest);

    TokenRefreshResponse refreshToken(TokenRefreshRequest request);

    MessageResponse logout(String username);
}
