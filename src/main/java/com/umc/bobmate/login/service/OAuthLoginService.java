package com.umc.bobmate.login.service;

import com.umc.bobmate.comment.domain.repository.CommentRepository;
import com.umc.bobmate.evaluation.domain.repository.EvaluationRepository;
import com.umc.bobmate.like.domain.repository.LikeRepository;
import com.umc.bobmate.login.jwt.token.AuthTokens;
import com.umc.bobmate.login.jwt.util.AuthTokensGenerator;
import com.umc.bobmate.login.oauth.dto.param.OAuthLoginParams;
import com.umc.bobmate.login.oauth.dto.response.OAuthInfoResponse;
import com.umc.bobmate.login.oauth.service.RequestOAuthInfoService;
import com.umc.bobmate.member.domain.Member;
import com.umc.bobmate.member.domain.OAuthProvider;
import com.umc.bobmate.member.domain.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthLoginService {
    private final MemberRepository memberRepository;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;
    private final EvaluationRepository evaluationRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private final RequestOAuthInfoService requestOAuthInfoService;

    @Transactional
    public AuthTokens login(OAuthLoginParams params) {
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
        Long memberId = findOrCreateMember(oAuthInfoResponse);
        return authTokensGenerator.generate(memberId);
    }

    @Transactional
    public void unlink(OAuthProvider oAuthProvider) {
        final Member loginMember = authTokensGenerator.getLoginMember();
        requestOAuthInfoService.unlink(oAuthProvider, loginMember.getSocialAccessToken());
        memberRepository.deleteMember(loginMember);
        likeRepository.deleteByMember(loginMember);
        commentRepository.deleteByMember(loginMember);
        evaluationRepository.deleteByMember(loginMember);
    }

    private Long findOrCreateMember(OAuthInfoResponse oAuthInfoResponse) {
        final Member member = memberRepository.findBySocialId(oAuthInfoResponse.getSocialId())
                .orElseGet(() -> newMember(oAuthInfoResponse));
        member.setSocialAccessToken(oAuthInfoResponse.getAccessToken());
        return member.getId();
    }

    private Member newMember(OAuthInfoResponse oAuthInfoResponse) {
        Member member = Member.builder()
                .socialId(oAuthInfoResponse.getSocialId())
                .name(oAuthInfoResponse.getNickname())
                .imageUrl(oAuthInfoResponse.getProfileImage())
                .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
                .socialAccessToken(oAuthInfoResponse.getAccessToken())
                .build();

        return memberRepository.save(member);
    }
}
