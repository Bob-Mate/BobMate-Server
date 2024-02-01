package com.umc.bobmate.evaluation.controller;

import com.umc.bobmate.evaluation.domain.Evaluation;
import com.umc.bobmate.evaluation.dto.EvaluationResponseDTO;
import com.umc.bobmate.evaluation.service.EvaluationService;
import com.umc.bobmate.global.apiPayload.ApiResponse;
import com.umc.bobmate.login.jwt.util.AuthTokensGenerator;
import com.umc.bobmate.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/evaluations")
public class EvaluationController {

    @Autowired
    private final EvaluationService evaluationService;
    AuthTokensGenerator authTokensGenerator;

    @PostMapping("/makeEvaluation")
    public ApiResponse<List<EvaluationResponseDTO>> evaluateContent(
            @RequestParam Long contentId,
            @RequestParam Long memberId,
            @RequestParam boolean isGood
    ) {
        //evaluationService.evaluateContent(contentId, memberId, isGood);
        //return ApiResponse.onSuccess();
        return null;
    }

    @PostMapping("/content/evaluation/{contentId}")
    public ApiResponse<List<EvaluationResponseDTO>> giveScore(@PathVariable String contentId,
                                                                Long memberId){

        memberId = authTokensGenerator.getLoginMemberId();

        List<Evaluation> eval = new ArrayList<>();
        for (Evaluation e : eval){
            if(e.isGood()){ //true인 경우
                // 다음에 해당 감정 선택시, 추천에 뜨게 하기
            } else{
                // 다음에 해당 감정을 섵택시 해당 컨텐츠 추천에 안 뜨게 하기
            }
        }
        return null;
    }

}
