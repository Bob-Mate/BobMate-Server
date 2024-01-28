package com.umc.bobmate.like.dto;

import com.umc.bobmate.like.domain.LikeType;

public class LikesRequestDTO {
    private Long memberId;
    private Long contentId; // or menuId, depending on your use case
    private LikeType type;
}
