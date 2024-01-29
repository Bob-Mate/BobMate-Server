package com.umc.bobmate.login.service;

import com.umc.bobmate.login.jwt.token.AuthTokens;
import com.umc.bobmate.login.jwt.util.AuthTokensGenerator;
import com.umc.bobmate.login.oauth.dto.info.OAuthInfoResponse;
import com.umc.bobmate.login.oauth.dto.param.OAuthLoginParams;
import com.umc.bobmate.login.oauth.service.RequestOAuthInfoService;
import com.umc.bobmate.member.domain.Member;
import com.umc.bobmate.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        return memberRepository.findBySocialId(oAuthInfoResponse.getSocialId())
                .map(Member::getId)
                .orElseGet(() -> newMember(oAuthInfoResponse));
    }

    private Long newMember(OAuthInfoResponse oAuthInfoResponse) {
        Member member = Member.builder()
                .socialId(oAuthInfoResponse.getSocialId())
                .name(oAuthInfoResponse.getNickname())
                .imageUrl(oAuthInfoResponse.getProfileImage())
                .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
                .build();

        return memberRepository.save(member).getId();
    }
}
