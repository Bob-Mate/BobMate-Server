package com.umc.bobmate.evaluation.service;

import com.umc.bobmate.evaluation.domain.repository.EvaluationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EvaluationService {
    private final EvaluationRepository evaluationRepository;

    public void evaluateContent(Long contentId, Long memberId, boolean isGood) {
    }
}
