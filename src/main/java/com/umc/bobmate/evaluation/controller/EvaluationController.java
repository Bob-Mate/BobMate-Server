package com.umc.bobmate.evaluation.controller;

import com.umc.bobmate.evaluation.dto.EvaluationResponseDTO;
import com.umc.bobmate.evaluation.service.EvaluationService;
import com.umc.bobmate.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/evaluations")
public class EvaluationController {

    @Autowired
    private final EvaluationService evaluationService;

    @PostMapping("/makeEvaluation")
    public ApiResponse<List<EvaluationResponseDTO>> evaluateContent(
            @RequestParam Long contentId,
            @RequestParam Long memberId,
            @RequestParam boolean isGood
    ) {
        evaluationService.evaluateContent(contentId, memberId, isGood);
        //return ApiResponse.onSuccess();
        return null;
    }
}
