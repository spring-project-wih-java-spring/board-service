package org.example.boardservice.dto;

import lombok.Value;
import org.example.boardservice.domain.Article;
import org.example.boardservice.domain.ArticleComment;
import org.example.boardservice.domain.UserAccount;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * DTO for {@link ArticleComment}
 */
@Value
public class ArticleWithCommentsDto implements Serializable {
    Long id;
    UserAccountDto userAccountDto;
    Set<ArticleCommentDto> articleCommentDtos;
    String title;
    String content;
    String hashtag;

    LocalDateTime createdAt;
    String createdBy;
    LocalDateTime modifiedAt;
    String modifiedBy;

    public static ArticleWithCommentsDto of(Long id, UserAccountDto userAccountDto, String content) {
        return ArticleWithCommentsDto.of(id, userAccountDto, null, content);
    }


    public static ArticleWithCommentsDto from(ArticleComment entity) {
        return new ArticleWithCommentsDto(
                entity.getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getArticleComments().stream()
                                .map(ArticleCommentDto::from)
                                        .collect(Collectors.toCollection(LinkedHashSet::new)),
                entity.getTitle(),
                entity.getContent(),
                entity.getHashtag(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

}