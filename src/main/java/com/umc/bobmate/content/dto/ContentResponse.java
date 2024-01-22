package com.umc.bobmate.content.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ContentResponse {

    private Long contentId;
    private String name;
    private String imgUrl;
    private String linkUrl;

}