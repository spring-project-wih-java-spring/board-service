package org.example.boardservice.dto;


import org.example.boardservice.domain.Hashtag;

import java.time.LocalDateTime;

public class HashtagDto {
    private Long id;
    private String hashtagName;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;

    public HashtagDto(Object o, String hashtagName, Object o1, Object o2, Object o3, Object o4) {
    }

    public static HashtagDto of(String hashtagName) {
        return new HashtagDto(null, hashtagName, null, null, null, null);
    }

    public static HashtagDto of(Long id, String hashtagName, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new HashtagDto(id, hashtagName, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static HashtagDto from(Hashtag entity) {
        return new HashtagDto(
                entity.getId(),
                entity.getHashtagName(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public Hashtag toEntity() {
        return Hashtag.of(hashtagName);
    }

}