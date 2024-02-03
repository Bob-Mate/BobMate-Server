package com.umc.bobmate.evaluation.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EvaluationResponseDTO {
    private Long memberId;
    private boolean isGood;
    private Long contentId;
}
