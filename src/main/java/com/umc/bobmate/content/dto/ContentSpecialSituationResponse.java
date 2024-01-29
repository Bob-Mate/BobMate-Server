package com.umc.bobmate.content.dto;

import com.umc.bobmate.content.domain.ContentType;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ContentSpecialSituationResponse {
    private Long contentId;
    private String name;
    private ContentType type;
    private String imgUrl;
    private String linkUrl;
    private List<String> genreList;
    private List<String> emotionList;
}
