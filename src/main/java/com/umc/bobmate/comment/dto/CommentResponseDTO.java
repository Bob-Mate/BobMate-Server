package com.umc.bobmate.comment.dto;

import com.umc.bobmate.content.domain.ContentType;
import com.umc.bobmate.content.domain.Emotion;
import com.umc.bobmate.content.domain.Genre;
import com.umc.bobmate.evaluation.domain.Evaluation;
import com.umc.bobmate.login.jwt.util.AuthTokensGenerator;
import com.umc.bobmate.member.domain.Member;
import lombok.*;

import java.util.List;


@Getter
@Builder
public class CommentResponseDTO {
//comment에서 음식+감정이 모두 겹치는 가장 많은 상황 4개 전달용
    private String food;
    private Emotion emotion;
    private Genre genre;
    private Long commentId;
}