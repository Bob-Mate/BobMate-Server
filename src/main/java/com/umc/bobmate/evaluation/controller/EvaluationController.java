package com.umc.bobmate.evaluation.controller;

import com.umc.bobmate.evaluation.domain.Evaluation;
import com.umc.bobmate.evaluation.domain.repository.EvaluationRepository;
import com.umc.bobmate.evaluation.dto.EvaluationRequestDTO;
import com.umc.bobmate.evaluation.service.EvaluationService;
import com.umc.bobmate.global.apiPayload.ApiResponse;
import com.umc.bobmate.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.umc.bobmate.global.apiPayload.code.status.ErrorStatus._BAD_REQUEST;
import static com.umc.bobmate.global.apiPayload.code.status.SuccessStatus._OK;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@RequestMapping("/api/v1/evaluation")
@Slf4j
public class EvaluationController {

    private final EvaluationService evaluationService;

    //Evaluation 처리만 하면 되고 그 결과를 Controller에서 처리하기
    @PostMapping("/create")
    public ApiResponse<Void> evaluateContents(@RequestBody EvaluationRequestDTO dto) {

        try {
            // 평가 엔티티를 저장하는게 끝 - 리포지토리(데이터베이스에) 그러고 끝
            evaluationService.saveEvaluation(dto);
            return ApiResponse.of(_OK);
        } catch (Exception e) {
            throw new GeneralException(_BAD_REQUEST);
        }
    }

    @PatchMapping("/update/{id}")
    public ApiResponse<Void> updateEvaluate(@PathVariable Long id, @RequestBody EvaluationRequestDTO dto) {

        try {
            // 평가 엔티티를 저장하는게 끝 - 리포지토리(데이터베이스에) 그러고 끝
            evaluationService.updateEvaluation(id, dto);
            return ApiResponse.of(_OK);
        } catch (Exception e) {
            throw new GeneralException(_BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> deleteEvaluation(@PathVariable Long id){
        try{
            evaluationService.deleteEvaluationById(id);
            return ApiResponse.of(_OK);
        } catch (Exception e){
            throw new GeneralException(_BAD_REQUEST);
        }
    }

}

