package com.umc.bobmate.content.dto;
import lombok.Getter;
@Getter
public class ContentRequestDTO {
    private String emotion;
    private String contentType;
    private String withWhom;
}
//    public static Content toEntity(ContentRequest contentRequest) {
//        return Content.builder()
//                .type(contentRequest.getType())
//                .genreList(contentRequest.getGenreList())
//                .emotionList(contentRequest.getEmotionList())
//                .build();
//    }


