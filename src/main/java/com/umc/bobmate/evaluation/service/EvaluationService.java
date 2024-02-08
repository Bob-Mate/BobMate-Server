package com.umc.bobmate.evaluation.service;

import com.umc.bobmate.content.domain.Content;
import com.umc.bobmate.content.domain.repository.ContentRepository;
import com.umc.bobmate.evaluation.domain.Evaluation;
import com.umc.bobmate.evaluation.domain.repository.EvaluationRepository;
import com.umc.bobmate.evaluation.dto.EvaluationRequestDTO;
import com.umc.bobmate.login.jwt.util.AuthTokensGenerator;
import com.umc.bobmate.member.domain.Member;
import com.umc.bobmate.member.domain.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EvaluationService {

    private final MemberRepository memberRepository;
    private final EvaluationRepository evaluationRepository;
    private final ContentRepository contentRepository;
    private final AuthTokensGenerator authTokensGenerator;

    public void saveEvaluation(EvaluationRequestDTO dto){
        Long memberId = authTokensGenerator.getLoginMemberId();

        // 평가 엔티티 생성
        Evaluation evaluation = Evaluation.builder()
                .isGood(dto.isGood())
                .build();

        // 멤버와 컨텐츠 설정
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found with id: " + memberId));
        Content content = contentRepository.findById(dto.getContentId())
                .orElseThrow(() -> new EntityNotFoundException("Content not found with id: " + dto.getContentId()));

        evaluation.setMember(member);
        evaluation.setContent(content);

        // 평가 저장
        evaluationRepository.save(evaluation);
    }


    public void updateEvaluation(Long id, EvaluationRequestDTO dto) {

        Evaluation target = evaluationRepository.findEvaluationByContentId(id);
        if (target != null) {
            target.setGood(dto.isGood());
            target.setContent(contentRepository.findById(dto.getContentId()).orElseThrow());
        }
        evaluationRepository.save(target);
    }

    public void deleteEvaluationById(Long id) {
        Evaluation e = evaluationRepository.findEvaluationByContentId(id);
        evaluationRepository.delete(e);
    }

}