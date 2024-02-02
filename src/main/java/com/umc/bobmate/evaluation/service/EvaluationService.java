package com.umc.bobmate.evaluation.service;

import com.umc.bobmate.content.controller.ContentController;
import com.umc.bobmate.content.domain.Content;
import com.umc.bobmate.content.service.ContentService;
import com.umc.bobmate.evaluation.domain.Evaluation;
import com.umc.bobmate.evaluation.domain.repository.EvaluationRepository;
import com.umc.bobmate.evaluation.dto.EvaluationRequestDTO;
import com.umc.bobmate.evaluation.dto.EvaluationResponseDTO;
import com.umc.bobmate.login.jwt.util.AuthTokensGenerator;
import com.umc.bobmate.member.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.umc.bobmate.evaluation.domain.Evaluation.*;

@Service
@Lazy
public class EvaluationService {
//    private final EvaluationRepository evaluationRepository;
//    private final ContentService contentService;
//    private final AuthTokensGenerator authTokensGenerator;
//
//
//    @Autowired
//    public EvaluationService(EvaluationRepository evaluationRepository, ContentService contentService, ContentController controller, EvaluationService evaluationService, AuthTokensGenerator authTokensGenerator) {
//        this.evaluationRepository = evaluationRepository;
//        this.contentService = contentService;
//        this.authTokensGenerator = authTokensGenerator;
//    }
//
//    public Long getCurrentMemberId() {
//        return authTokensGenerator.getLoginMemberId();
//    }
//
//    public Member getCurrentMember() {
//        return authTokensGenerator.getLoginMember();
//    }
//
//
//    public List<EvaluationResponseDTO> saveEvaluations(List<EvaluationRequestDTO> dtos) {
//        List<EvaluationResponseDTO> responseDTOs = new ArrayList<>();
//
//        for (EvaluationRequestDTO dto : dtos) {
//
//            Optional<Content> content = contentService.getContentById(dto.getContentId());
//
//            // Create an Evaluation entity using the builder pattern
//            Evaluation evaluation = Evaluation.builder()
//                    .isGood(dto.isGood())
//                    .member(authTokensGenerator.getLoginMember())
//                    .content(contentService.getContentById(dto.getContentId()).orElse(null))
//                    .build();
//
//            // Save the Evaluation entity using the repository
//            Evaluation savedEvaluation = evaluationRepository.save(evaluation);
//
//            // Create a response DTO from the saved Evaluation entity
//            EvaluationResponseDTO responseDTO = EvaluationResponseDTO.builder()
//                    .contentId(savedEvaluation.getContent().getId())
//                    .isGood(savedEvaluation.isGood())
//                    .memberId(authTokensGenerator.getLoginMemberId())
//                    .build();
//
//            responseDTOs.add(responseDTO);
//        }
//
//        return responseDTOs;
//    }

}
//    public List<EvaluationResponseDTO> saveEvaluations(List<EvaluationRequestDTO> dtos) {
//        List<EvaluationResponseDTO> responseDTOs = new ArrayList<>();
//
//        for (EvaluationRequestDTO dto : dtos) {
//            // Find Content by contentId
//            Content content = contentService.getContentById(dto.getContentId());
//
//            // Create an Evaluation entity using the builder pattern
//            Evaluation evaluation = new Evaluation.Builder()
//                    .isGood(dto.isGood())
//                    .member(new Member.Builder().id(dto.getMemberId()).build())
//                    .content(content)
//                    .build();
//
//            // Save the Evaluation entity using the repository
//            Evaluation savedEvaluation = evaluationRepository.save(evaluation);
//
//            // Create a response DTO from the saved Evaluation entity
//            EvaluationResponseDTO responseDTO = EvaluationResponseDTO.builder()
//                    .contentId(savedEvaluation.getContent().getId())
//                    .isGood(savedEvaluation.isGood())
//                    .memberId(savedEvaluation.getMember().getId())
//                    .build();
//
//            responseDTOs.add(responseDTO);
//        }
//
//        return responseDTOs;
//    }

//    public List<Evaluation> saveEvaluation(EvaluationRequestDTO dto) {
//        // Create an Evaluation entity using the builder pattern
//        Long memberId = authTokensGenerator.getLoginMemberId();
//        List<Evaluation> eval = new ArrayList<>();
//
//        Evaluation evaluation = new Evaluation(memberId, dto.isGood(), dto.getContentId());
//                .isGood(dto.isGood())
//                .member(new Member.Builder().id(dto.getMemberId()).build())
//                .content(new Content.Builder().id(dto.getContentId()).build())
//                .build();
//
//        // Save the Evaluation entity using the repository
//        Evaluation savedEvaluation = evaluationRepository.save(evaluation);
//
//        // Return the saved Evaluation in a list
//        return Collections.singletonList(savedEvaluation);
//    }




