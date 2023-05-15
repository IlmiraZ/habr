package ru.ilmira.habr.service;

import ru.ilmira.habr.service.dto.TopicDto;

import java.util.List;

/**
 * TopicService.
 *
 * @author Zalyaletdinova Ilmira
 */
public interface TopicService {
    List<TopicDto> findAll();

}
