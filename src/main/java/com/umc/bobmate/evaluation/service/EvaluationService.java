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
        Evaluation evaluation = Evaluation.builder().build();

        // 멤버와 컨텐츠 설정
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found with id: " + memberId));
        Content content = contentRepository.findById(dto.getContentId())
                .orElseThrow(() -> new EntityNotFoundException("Content not found with id: " + dto.getContentId()));

        Evaluation previousEvaluation = evaluationRepository.findEvaluationByContentIdAndMemberId(memberId, dto.getContentId());
        if (previousEvaluation != null) {
            // do noting
        }

        else {
            if (dto.isGood()==true){
                evaluation.setMember(member);
                evaluation.setContent(content);
                evaluation.setGood(dto.isGood());
                evaluation.setScore(1L);
                evaluationRepository.save(evaluation);

            } else{
                evaluation.setMember(member);
                evaluation.setContent(content);
                evaluation.setGood(dto.isGood());
                evaluation.setScore(0L);
                evaluationRepository.save(evaluation);
            }
        }
    }

    public void updateEvaluation(Long id, EvaluationRequestDTO dto) {
        Long memberId = authTokensGenerator.getLoginMemberId();
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found with id: " + memberId));
        Content content = contentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Content not found with id: " + dto.getContentId()));

        Evaluation target = evaluationRepository.findEvaluationByContentIdAndMemberId(memberId, dto.getContentId());
        Long contentId = evaluationRepository.findEvaluationByContentIdAndMemberId(memberId, id).getContent().getId();
        System.out.println("id와 content 비교"+ id + " "+ contentId);
        if (target != null && id.equals(dto.getContentId())) {
            boolean currentGood = dto.isGood(); // 현재의 isGood() 값을 저장합니다.

            if (currentGood==true) {
                target.setGood(currentGood); // 현재 값으로 isGood()을 수정합니다.
                target.setContent(content);
                target.setMember(member);
                target.setScore(1L);
            }

            else {
                target.setGood(currentGood); // 현재 값으로 isGood()을 수정합니다.
                target.setContent(content);
                target.setMember(member);
                target.setScore(0L);
            }

            evaluationRepository.save(target);
        }
    }

    public void deleteEvaluationById(Long id) {
        Long memberId = authTokensGenerator.getLoginMemberId();
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found with id: " + memberId));
        Content content = contentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Content not found with id: " + id));

        Evaluation target = evaluationRepository.findEvaluationByContentIdAndMemberId(memberId, content.getId());
        evaluationRepository.delete(target);
    }

}