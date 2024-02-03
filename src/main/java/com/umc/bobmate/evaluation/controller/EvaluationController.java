package com.umc.bobmate.evaluation.controller;

import com.umc.bobmate.evaluation.service.EvaluationService;
import com.umc.bobmate.login.jwt.util.AuthTokensGenerator;
import lombok.RequiredArgsConstructor;
<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

=======
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
>>>>>>> ff82077c6f0f16f906e47ae39d6233050ac19f2a

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
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


