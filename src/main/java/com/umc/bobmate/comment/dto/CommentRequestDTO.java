package com.umc.bobmate.comment.dto;

import com.umc.bobmate.content.domain.Emotion;
import com.umc.bobmate.content.domain.Genre;
import com.umc.bobmate.member.domain.Member;
import lombok.Getter;

@Getter
public class CommentRequestDTO {
    private Long id;
    private Emotion emotion;
    private Genre genre;
    private String food;
    private Member memberId;
}
