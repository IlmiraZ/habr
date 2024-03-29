package ru.ilmira.habr.service;

import ru.ilmira.habr.persist.repository.TopicRepository;
import ru.ilmira.habr.service.dto.TopicMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;


/**
 * Unit tests for TopicServiceImpl.
 *
 * @author Zalyaletdinova Ilmira
 * @see TopicService
 * @see TopicServiceImpl
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Tests for TopicServiceImpl")
public class TopicServiceImplTest {
    private TopicService underTest;

    @Mock
    private TopicRepository topicRepository;

    @Mock
    private TopicMapper topicMapper;

    @BeforeEach
    void setUp() {
        underTest = new TopicServiceImpl(topicRepository, topicMapper);
    }

    @Test
    @DisplayName("find all topics")
    void findAllTopics() {
        // given
        // when
        underTest.findAll();
        // then
        verify(topicRepository).findAll();
    }
}
