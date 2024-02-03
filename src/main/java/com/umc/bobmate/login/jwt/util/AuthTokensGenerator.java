package com.umc.bobmate.login.jwt.util;

import com.umc.bobmate.global.apiPayload.exception.GeneralException;
import com.umc.bobmate.login.jwt.token.AuthTokens;
import com.umc.bobmate.member.domain.Member;
import com.umc.bobmate.member.domain.repository.MemberRepository;
import com.umc.bobmate.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Base64;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;

import static com.umc.bobmate.global.apiPayload.code.status.ErrorStatus.INVALID_MEMBER_ID;

@Component
@RequiredArgsConstructor
public class AuthTokensGenerator {
    private static final String BEARER_TYPE = "Bearer";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 5;        // 5 Hour
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;  // 7 Day

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    public AuthTokens generate(Long memberId) {
        long now = (new Date()).getTime();
        Date accessTokenExpiredAt = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        Date refreshTokenExpiredAt = new Date(now + REFRESH_TOKEN_EXPIRE_TIME);

        String subject = memberId.toString();
        String accessToken = jwtTokenProvider.generate(subject, accessTokenExpiredAt);
        String refreshToken = jwtTokenProvider.generate(subject, refreshTokenExpiredAt);

        return AuthTokens.of(accessToken, refreshToken, BEARER_TYPE, ACCESS_TOKEN_EXPIRE_TIME / 1000L);
    }

    private Long extractMemberId(String accessToken) {
        return Long.valueOf(jwtTokenProvider.extractSubject(accessToken));
    }

    public Long getLoginMemberId() {
        HttpServletRequest httpServletRequest
                = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        final String accessToken = httpServletRequest.getHeader("Authorization");
        System.out.println("Received accessToken: " + accessToken);  // 확인용 로그 추가
        return extractMemberId(accessToken);
    }

    public Member getLoginMember() {
        final Long loginId = getLoginMemberId();
        return memberRepository.findById(loginId)
                .orElseThrow(() -> new GeneralException(INVALID_MEMBER_ID));
    }
}
