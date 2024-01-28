package com.umc.bobmate.content.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ContentResponseDTO {

    private Long contentId;
    private String name;
    private String type;
    private String imgUrl;
    private String linkUrl;
    private List<String> genreList;
    private List<String> emotionList;
}

//    public static ContentResponse from(Content content){
//        return ContentResponse.builder()
//                .name(content.getName())
//                .imgUrl(content.getImgUrl())
//                .linkUrl(content.getLinkUrl())
//                .build();
//    }

