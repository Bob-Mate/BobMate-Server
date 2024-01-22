package com.umc.bobmate.content.dto;

import com.umc.bobmate.content.domain.Content;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ContentResponse {

    private Long contentId;
    private String name;
    private String imgUrl;
    private String linkUrl;


    public static ContentResponse from(Content content){
        return ContentResponse.builder()
                .name(content.getName())
                .imgUrl(content.getImgUrl())
                .linkUrl(content.getLinkUrl())
                .build();
    }

}