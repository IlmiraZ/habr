package ru.ilmira.habr.service;


import lombok.RequiredArgsConstructor;
import ru.ilmira.habr.persist.repository.TopicRepository;
import ru.ilmira.habr.service.dto.TopicDto;
import ru.ilmira.habr.service.dto.TopicMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * TopicServiceImpl.
 *
 * @author Zalyaletdinova Ilmira
 */
@RequiredArgsConstructor
@Service
public class TopicServiceImpl implements TopicService {
    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;

    @Override
    public List<TopicDto> findAll() {

        return topicRepository.findAll()
                .stream()
                .map(topicMapper::fromTopic)
                .collect(Collectors.toList());
    }
}
