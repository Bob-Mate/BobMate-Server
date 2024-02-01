package com.umc.bobmate.evaluation.service;

import com.umc.bobmate.content.controller.ContentController;
import com.umc.bobmate.content.domain.Content;
import com.umc.bobmate.content.dto.ContentResponseDTO;
import com.umc.bobmate.content.service.ContentService;
import com.umc.bobmate.evaluation.domain.Evaluation;
import com.umc.bobmate.evaluation.domain.repository.EvaluationRepository;
import com.umc.bobmate.global.apiPayload.ApiResponse;
import com.umc.bobmate.login.jwt.util.AuthTokensGenerator;
import com.umc.bobmate.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EvaluationService {
    private final EvaluationRepository evaluationRepository;
    private final ContentService contentService;
    private final ContentController controller;
    @Autowired
    private final AuthTokensGenerator authTokensGenerator;

    public Long getCurrentMemberId() {
        return authTokensGenerator.getLoginMemberId();
    }

    public Member getCurrentMember() {
        return authTokensGenerator.getLoginMember();
    }


//    public void saveEvaluation(Long memberId, Long contentId, boolean isGood) {
//        // ContentController에서 추천 컨텐츠를 가져오는 메서드 호출
//        ApiResponse<List<ContentResponseDTO>> apiResponse =
//                controller.recommendContent("emotion", "withWhom", ContentType TYPE);
//
//        // ApiResponse에서 데이터를 추출
//        List<ContentResponseDTO> contentResponseDTOList = apiResponse.getData();
//
//        if (member != null && contentResponseDTOList != null && !contentResponseDTOList.isEmpty()) {
//            // 추천 컨텐츠 중 첫 번째 컨텐츠를 선택
//            ContentResponseDTO firstRecommendedContent = contentResponseDTOList.get(0);
//
//            // 여기에서 필요한 데이터를 추출하여 Evaluation 객체를 생성
//            Evaluation evaluation = new Evaluation();
//            evaluation.setMember(member);
//            evaluation.setContentId(firstRecommendedContent.getContentId());
//            evaluation.setIsGood(isGood);
//
//            // Evaluation을 저장
//            evaluationRepository.save(evaluation);
//        } else {
//            // 멤버나 추천 컨텐츠가 존재하지 않는 경우 처리 로직 추가
//        }



//        Member member = getCurrentMember();
//        List<Content> content = controller.recommendContent()// 컨텐츠를 가져오는 로직 (예: contentService.findById(contentId))
//
//        if (member != null && content != null) {
//            Evaluation evaluation =
//            evaluationRepository.save(evaluation);
//        } else {
//            // 멤버나 컨텐츠가 존재하지 않는 경우 처리 로직 추가
//        }

}
