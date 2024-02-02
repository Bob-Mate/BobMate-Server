package com.umc.bobmate.evaluation.domain.repository;


import com.umc.bobmate.content.controller.ContentController;
import com.umc.bobmate.content.domain.Content;
import com.umc.bobmate.evaluation.domain.Evaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

}
