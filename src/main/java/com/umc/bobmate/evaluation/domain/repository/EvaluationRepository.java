package com.umc.bobmate.evaluation.domain.repository;


import com.umc.bobmate.content.controller.ContentController;
import com.umc.bobmate.content.domain.Content;
import com.umc.bobmate.evaluation.domain.Evaluation;
<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
=======
import com.umc.bobmate.member.domain.Member;
>>>>>>> ff82077c6f0f16f906e47ae39d6233050ac19f2a
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

<<<<<<< HEAD
=======
    @Modifying
    @Query("update Evaluation e set e.status = 'DELETED'")
    void deleteByMember(@Param("member") Member loginMember);
>>>>>>> ff82077c6f0f16f906e47ae39d6233050ac19f2a
}
