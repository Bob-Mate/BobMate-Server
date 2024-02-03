package com.umc.bobmate.evaluation.service;

import com.umc.bobmate.content.dto.ContentResponseDTO;
import com.umc.bobmate.evaluation.domain.Evaluation;
import com.umc.bobmate.evaluation.domain.repository.EvaluationRepository;
import com.umc.bobmate.evaluation.dto.EvaluationRequestDTO;
import com.umc.bobmate.evaluation.dto.EvaluationResponseDTO;
import com.umc.bobmate.login.jwt.util.AuthTokensGenerator;
import com.umc.bobmate.member.domain.Member;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class EvaluationService {
    private final AuthTokensGenerator authTokensGenerator;
    private final EvaluationRepository evaluationRepository;


    public EvaluationService(AuthTokensGenerator authTokensGenerator, EvaluationRepository evaluationRepository) {
        this.authTokensGenerator = authTokensGenerator;
        this.evaluationRepository = evaluationRepository;
    }


    // 리턴된 contentResponseDTO의 contentID를 기준으로 평가하기 : 지수한테 카톡후 다른 코드 시도
//
//    public List<Evaluation> saveEvaluations(List<EvaluationRequestDTO> dtos, List<ContentResponseDTO> cdtos) {
//        List<Evaluation> evals = new ArrayList<>();
//        //Long contentId1 = contentResponseDTO.getContentId();
//
//        // Long contentId = contentService.recommendContents().get(0);
//        for (ContentResponseDTO dto : cdtos) {
//
//            for (EvaluationRequestDTO ev : dtos){
//                Evaluation evaluation = Evaluation.builder()
//                        .isGood(ev.isGood())
//                        .member(authTokensGenerator.getLoginMember())
//                        //.content(ev.getContentId())
//                        .build();
//
//                // Save the Evaluation entity using the repository
//                Evaluation savedEvaluation = evaluationRepository.save(evaluation);
//                evals.add(savedEvaluation);
//            }
//            // Create an Evaluation entity using the builder pattern
//
//        }
//
//        return evals;
//    }

    public List<Evaluation> saveEvaluations(List<EvaluationRequestDTO> dtos) {
        List<Evaluation> evals = new ArrayList<>();
        //Long contentId1 = contentResponseDTO.getContentId();

        // Long contentId = contentService.recommendContents().get(0);
        for (EvaluationRequestDTO dto : dtos) {
            // Create an Evaluation entity using the builder pattern
            Evaluation evaluation = Evaluation.builder()
                    .isGood(dto.isGood())
                    .member(authTokensGenerator.getLoginMember())
                    .content(dto.getContentId())
                    .build();

            // Save the Evaluation entity using the repository
            Evaluation savedEvaluation = evaluationRepository.save(evaluation);
            evals.add(savedEvaluation);
        }

        return evals;
    }



}




