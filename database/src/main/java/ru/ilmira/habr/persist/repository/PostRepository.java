package ru.ilmira.habr.persist.repository;

import ru.ilmira.habr.persist.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {
    Optional<Post> findById(@Param("postId") Long postId);
}
