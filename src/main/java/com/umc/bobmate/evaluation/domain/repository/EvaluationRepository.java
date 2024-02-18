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


    @Query("SELECT e FROM Evaluation e where (e.member.id=:memberId and e.content.id=:contentId and e.status='ACTIVE')")
    Evaluation findEvaluationByContentIdAndMemberId(@Param("memberId") Long memberId, @Param("contentId") Long contentId);

    //SELECT e.content_id, SUM(e.score) FROM evaluation e
    // WHERE e.is_good=true GROUP BY e.content_id, e.is_good ORDER BY SUM(e.score) DESC
    @Query("SELECT DISTINCT e FROM Evaluation e " +
            "WHERE e.isGood=true and e.score IS NOT NULL GROUP BY e.content.id, e.isGood ORDER BY SUM(e.score) DESC")
    List<Evaluation> findTopContentSumScore();



}
