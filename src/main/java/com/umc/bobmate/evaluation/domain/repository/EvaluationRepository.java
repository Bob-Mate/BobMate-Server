package com.umc.bobmate.evaluation.domain.repository;

import com.umc.bobmate.content.domain.Content;
import com.umc.bobmate.evaluation.domain.Evaluation;
import com.umc.bobmate.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import static com.umc.bobmate.common.BaseEntityStatus.DELETED;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    @Modifying
    @Query("update Evaluation e set e.status = 'DELETED'")
    void deleteByMember(@Param("member") Member loginMember);

    Evaluation findEvaluationByContentId(Long id);

}
