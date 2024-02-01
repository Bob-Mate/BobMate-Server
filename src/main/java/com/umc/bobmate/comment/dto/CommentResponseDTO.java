package com.umc.bobmate.comment.dto;

import com.umc.bobmate.content.domain.ContentType;
import com.umc.bobmate.content.domain.Emotion;
import com.umc.bobmate.content.domain.Genre;
import com.umc.bobmate.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CommentResponseDTO {
//    private Long contentId;
//    private String name;
//    private ContentType type;
//    private String imgUrl;
//    private String linkUrl;
//    private String genre;
//    private String emotion;
//    private Member memberID;

    private String food;
    private Emotion emotion;
    private Genre genre;
    private Long memberId;
}
