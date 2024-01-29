package com.umc.bobmate.evaluation.dto;

import com.umc.bobmate.content.domain.Content;
import com.umc.bobmate.member.domain.Member;
import jakarta.persistence.*;

public class EvaluationRequestDTO {
    private Long memberId;
    private boolean isGood;
    private Long contentId;
}
