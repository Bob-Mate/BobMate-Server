package com.umc.bobmate.content.dto;

import com.umc.bobmate.content.domain.ContentType;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ContentResponseDTO {

    private Long contentId;
    private String name;
    private ContentType type;
    private String imgUrl;
    private String linkUrl;
    private List<String> genreList;
    private List<String> emotionList;
//    private Long memberId;
}

//    public static ContentResponse from(Content content){
//        return ContentResponse.builder()
//                .name(content.getName())
//                .imgUrl(content.getImgUrl())
//                .linkUrl(content.getLinkUrl())
//                .build();
//    }

