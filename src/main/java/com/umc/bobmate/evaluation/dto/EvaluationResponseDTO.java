package com.umc.bobmate.evaluation.dto;

import com.umc.bobmate.content.domain.Content;
import com.umc.bobmate.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EvaluationResponseDTO {
//    private Long memberId;
//    private boolean isGood;
//    private Long contentId;

    private Member memberId;
    private Content contentId;
    private boolean isGood;
}
