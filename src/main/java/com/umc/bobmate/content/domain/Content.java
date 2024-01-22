package com.umc.bobmate.content.domain;

import static lombok.AccessLevel.PROTECTED;

import com.umc.bobmate.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Content extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ContentType type;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Enumerated(EnumType.STRING)
    private Emotion emotion;

    private String imageUrl;
    private String linkUrl;

    @Builder
    public Content(String name, ContentType type, Genre genre, String imageUrl, String linkUrl) {
        this.name = name;
        this.type = type;
        this.genre = genre;
        this.imageUrl = imageUrl;
        this.linkUrl = linkUrl;
    }
}
