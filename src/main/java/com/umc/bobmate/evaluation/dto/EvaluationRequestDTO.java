package com.umc.bobmate.evaluation.dto;

import com.umc.bobmate.content.domain.Content;
import com.umc.bobmate.content.domain.repository.ContentRepository;
import com.umc.bobmate.content.service.ContentService;
import com.umc.bobmate.evaluation.domain.Evaluation;
import com.umc.bobmate.login.jwt.util.AuthTokensGenerator;
import com.umc.bobmate.member.domain.Member;
import com.umc.bobmate.member.domain.repository.MemberRepository;
import com.umc.bobmate.member.service.MemberService;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;


@Setter
@Getter
public class EvaluationRequestDTO {
    private Long contentId;
    private boolean isGood;
}
