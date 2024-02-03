package com.umc.bobmate.content.dto;
import lombok.Builder;
import lombok.Getter;
@Getter
@Builder
public class ContentRequestDTO {
    private String emotion;
    private String contentType;
    private String withWhom;
}


