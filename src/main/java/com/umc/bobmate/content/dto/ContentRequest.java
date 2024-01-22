package com.umc.bobmate.content.dto;

import com.umc.bobmate.content.domain.Content;
import com.umc.bobmate.content.domain.ContentType;
import com.umc.bobmate.content.domain.Emotion;
import com.umc.bobmate.content.domain.Genre;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ContentRequest {
    @Enumerated(EnumType.STRING)
    private ContentType type;

    @Column(name = "genre")
    private List<String> genreList = new ArrayList<>();

//    @Enumerated(EnumType.STRING)
//    private Emotion emotion;

    @Column(name = "emotion")
    private List<String> emotionList = new ArrayList<>();

    public static Content toEntity(ContentRequest contentRequest) {
        return Content.builder()
                .type(contentRequest.getType())
                .genreList(contentRequest.getGenreList())
                .emotionList(contentRequest.getEmotionList())
                .build();
    }

}
