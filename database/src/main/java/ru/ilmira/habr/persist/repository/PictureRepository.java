package ru.ilmira.habr.persist.repository;

import ru.ilmira.habr.persist.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface - PictureRepository.
 *
 * @author Karachev Sasha
 */
public interface PictureRepository extends JpaRepository<Picture, Long> {
}
