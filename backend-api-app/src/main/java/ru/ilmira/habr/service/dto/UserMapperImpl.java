package ru.ilmira.habr.service.dto;

import ru.ilmira.habr.persist.model.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto fromUser(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .aboutMe(user.getAboutMe())
                .birthday(user.getBirthday())
                .created(user.getCreated())
                .updated(user.getUpdated())
                .condition(user.getCondition().name())
                .roles(user.getRoles().stream()
                        .map(role -> role.getName().name())
                        .collect(Collectors.toSet()))
                .build();
    }
}
