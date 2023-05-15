package ru.ilmira.habr.persist.repository;

import ru.ilmira.habr.persist.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Optional<Topic> findByName(String name);
}
