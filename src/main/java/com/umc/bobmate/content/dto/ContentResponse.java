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


    public static Content toEntity(ContentResponse contentResponse) {
        return Content.builder()
                .name(contentResponse.getName())
                .imgUrl(contentResponse.getImgUrl())
                .linkUrl(contentResponse.getLinkUrl())
                .build();
    }

}