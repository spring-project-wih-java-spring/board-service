package org.example.boardservice.dto;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link org.example.boardservice.domain.Article}
 */
@Value
public class ArticleUpdateDto implements Serializable {
    String title;
    String content;
    String hashtag;

    public static ArticleUpdateDto of (String title, String content, String hashtag) {
        return new ArticleUpdateDto(title, content,hashtag);
    }
}