package com.umc.bobmate.content.dto;

import com.umc.bobmate.content.domain.ContentType;
import lombok.Getter;

@Getter
public class ContentSpecialSituationRequest {
    private Long situationId;
    // 1 : 비오는 날 혼술
    // 2 : 자취방에서 친구들과 치킨 먹는 중
    // 3 : 너무 짜증나서 야식 먹는 중
    // 4 : 시험 기간 기념 떡볶이 먹는 중
    private ContentType contentType;
}
