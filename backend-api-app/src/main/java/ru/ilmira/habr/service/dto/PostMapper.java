package ru.ilmira.habr.service.dto;

import ru.ilmira.habr.persist.model.Post;

/**
 * Mapper interface for Post and PostDto.
 *
 * @see PostDto
 * @see ru.ilmira.habr.persist.model.Post
 *
 * @author Karachev Sasha
 */
public interface PostMapper {

    /**
     * Transform Post object to PostDto.
     *
     * @param post - database entity.
     * @return PostDto - representation of the Post object in the service layer.
     */
    PostDto fromPost(Post post);
}
