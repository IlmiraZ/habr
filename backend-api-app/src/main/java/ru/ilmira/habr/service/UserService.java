package ru.ilmira.habr.service;

import ru.ilmira.habr.payload.request.UpdateUserInfoRequest;
import ru.ilmira.habr.payload.response.MessageResponse;
import ru.ilmira.habr.persist.model.EUserCondition;
import ru.ilmira.habr.service.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;

import java.util.Optional;
import java.util.Set;

public interface UserService {

    Page<UserDto> findAll(Optional<String> username,
                          Optional<String> firstName,
                          Optional<String> lastName,
                          Optional<String> condition,
                          Optional<Integer> page,
                          Optional<Integer> size,
                          Optional<String> sortField,
                          Optional<Direction> direction);

    Optional<UserDto> findById(long userId);

    Optional<UserDto> findByUsername(String username);

    Optional<UserDto> findByUsername(String username, EUserCondition condition);

    MessageResponse deleteById(Long userId);

    UserDto update(String username, UpdateUserInfoRequest updateUserInfoRequest);

    MessageResponse promote(Long userId, Set<String> roles);

    MessageResponse condition(Long userId, EUserCondition condition);
}
