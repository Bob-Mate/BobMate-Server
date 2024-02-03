package com.umc.bobmate.evaluation.controller;

import com.umc.bobmate.content.domain.Content;
import com.umc.bobmate.content.domain.repository.ContentRepository;
import com.umc.bobmate.content.dto.ContentResponseDTO;
import com.umc.bobmate.evaluation.domain.Evaluation;
import com.umc.bobmate.evaluation.domain.repository.EvaluationRepository;
import com.umc.bobmate.evaluation.dto.EvaluationRequestDTO;
import com.umc.bobmate.evaluation.dto.EvaluationResponseDTO;
import com.umc.bobmate.evaluation.service.EvaluationService;
import com.umc.bobmate.global.apiPayload.ApiResponse;
import com.umc.bobmate.global.apiPayload.exception.GeneralException;
import com.umc.bobmate.login.jwt.util.AuthTokensGenerator;
import com.umc.bobmate.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.umc.bobmate.global.apiPayload.code.status.ErrorStatus._BAD_REQUEST;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/evaluations")
public class EvaluationController {

    private EvaluationService evaluationService;
    AuthTokensGenerator authTokensGenerator;

    @Autowired
    public void setEvaluationService(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    //Evaluation 처리만 하면 되고 그 결과를 contentCOntroller에서 처리하기
//    @PostMapping("/makeEvaluations")
//    public ApiResponse<List<EvaluationResponseDTO>> evaluateContents(
//            @RequestBody List<EvaluationRequestDTO> dtos) {
//
//        try {
//            // Call the service to save the evaluations
//
//            List<Evaluation> eval = evaluationService.saveEvaluations(dtos);
//
//            List<EvaluationResponseDTO> responseDTOs = eval.stream()
//                    .map(evaluation -> EvaluationResponseDTO.builder()
//                            .contentId(evaluation.getContent().getId())
//                            .memberId(authTokensGenerator.getLoginMemberId())
//                            .isGood(evaluation.isGood())
//                            .build())
//                    .collect(Collectors.toList());
//
//
//            return ApiResponse.onSuccess(responseDTOs);
//        } catch (Exception e) {
//            throw new GeneralException(_BAD_REQUEST);
//        }
//    }
}


