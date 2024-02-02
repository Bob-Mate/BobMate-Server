package com.umc.bobmate.evaluation.controller;

import com.umc.bobmate.evaluation.service.EvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class EvaluationController {
    private final EvaluationService evaluationService;
}
