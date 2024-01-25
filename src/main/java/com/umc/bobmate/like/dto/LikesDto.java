package com.umc.bobmate.like.dto;

import com.umc.bobmate.content.domain.Content;
import com.umc.bobmate.like.domain.LikeType;
import com.umc.bobmate.like.domain.Likes;
import com.umc.bobmate.member.domain.Member;
import com.umc.bobmate.menu.domain.Menu;
import jakarta.persistence.*;

public class LikesDto {

    private Long id;

    @Enumerated(EnumType.STRING)
    private LikeType type;

    private Member member;

    private Content content;

    private Menu menu;

    public LikesDto(Likes like){
        id = like.ge
    }
}
