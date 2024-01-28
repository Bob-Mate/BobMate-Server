package com.umc.bobmate.member.service;

import com.umc.bobmate.global.apiPayload.exception.GeneralException;
import com.umc.bobmate.member.domain.Member;
import com.umc.bobmate.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.umc.bobmate.global.apiPayload.code.status.ErrorStatus.INVALID_MEMBER_ID;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member findById(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new GeneralException(INVALID_MEMBER_ID));
    }
}
