package ru.ilmira.habr.service.dto;

import ru.ilmira.habr.persist.model.User;

public interface UserMapper {

    UserDto fromUser(User user);
}
