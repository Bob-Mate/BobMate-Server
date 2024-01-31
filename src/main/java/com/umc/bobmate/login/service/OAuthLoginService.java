package com.umc.bobmate.login.service;

<<<<<<< Updated upstream
=======
import com.umc.bobmate.comment.domain.repository.CommentRepository;
import com.umc.bobmate.common.BaseEntityStatus;
import com.umc.bobmate.evaluation.domain.repository.EvaluationRepository;
import com.umc.bobmate.like.domain.repository.LikeRepository;
>>>>>>> Stashed changes
import com.umc.bobmate.login.jwt.token.AuthTokens;
import com.umc.bobmate.login.jwt.util.AuthTokensGenerator;
import com.umc.bobmate.login.oauth.dto.info.OAuthInfoResponse;
import com.umc.bobmate.login.oauth.dto.param.OAuthLoginParams;
import com.umc.bobmate.login.oauth.service.RequestOAuthInfoService;
import com.umc.bobmate.member.domain.Member;
import com.umc.bobmate.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.umc.bobmate.common.BaseEntityStatus.*;

@Service
@RequiredArgsConstructor
public class OAuthLoginService {
    private final MemberRepository memberRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private final RequestOAuthInfoService requestOAuthInfoService;

    public AuthTokens login(OAuthLoginParams params) {
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
        Long memberId = findOrCreateMember(oAuthInfoResponse);
        return authTokensGenerator.generate(memberId);
    }

    private Long findOrCreateMember(OAuthInfoResponse oAuthInfoResponse) {
        final Member member = memberRepository.findBySocialId(oAuthInfoResponse.getSocialId())
                .orElseGet(() -> newMember(oAuthInfoResponse));
        member.setSocialAccessToken(oAuthInfoResponse.getAccessToken());
        member.setStatus(ACTIVE);
        return member.getId();
    }

    private Member newMember(OAuthInfoResponse oAuthInfoResponse) {
        Member member = Member.builder()
                .socialId(oAuthInfoResponse.getSocialId())
                .name(oAuthInfoResponse.getNickname())
                .imageUrl(oAuthInfoResponse.getProfileImage())
                .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
                .build();

        return memberRepository.save(member);
    }
}
