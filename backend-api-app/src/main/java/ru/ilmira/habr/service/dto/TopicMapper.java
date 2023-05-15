package ru.ilmira.habr.service.dto;

import ru.ilmira.habr.persist.model.Topic;

/**
 * TopicMapper.
 *
 * @author Zalyaletdinova Ilmira
 */
public interface TopicMapper {
    TopicDto fromTopic(Topic topic);
}
