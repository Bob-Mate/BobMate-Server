package com.umc.bobmate.evaluation.domain.repository;


import com.umc.bobmate.evaluation.domain.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
}
