package ru.ilmira.habr.service.dto;

import ru.ilmira.habr.persist.model.Post;
import ru.ilmira.habr.persist.model.Tag;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Implementation of the PostMapper interface.
 *
 * @see PostMapper
 * @author Karachev Sasha
 */
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public PostDto fromPost(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .created(post.getCreated())
                .updated(post.getUpdated())
                .title(post.getTitle())
                .content(post.getContent())
                .description(post.getDescription())
                .condition(post.getCondition().name())
                .owner(post.getOwner().getUsername())
                .topic(post.getTopic().getName())
                .mainPictureId(post.getMainPictureId())
                .pictures(post.getPictures().stream()
                        .map(pic -> PictureData.builder()
                                .id(pic.getId())
                                .name(pic.getName())
                                .build())
                        .collect(Collectors.toList()))
                .tags(post.getTags().stream()
                        .map(Tag::getName)
                        .collect(Collectors.toSet()))
                .build();
    }
}
