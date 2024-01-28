package com.umc.bobmate.member.service;

import com.umc.bobmate.global.apiPayload.exception.GeneralException;
import com.umc.bobmate.login.jwt.util.AuthTokensGenerator;
import com.umc.bobmate.member.domain.Member;
import com.umc.bobmate.member.domain.repository.CustomMemberRepository;
import com.umc.bobmate.member.domain.repository.MemberRepository;
import com.umc.bobmate.member.dto.request.PreferenceUploadRequest;
import com.umc.bobmate.member.dto.response.PreferenceResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.umc.bobmate.global.apiPayload.code.status.ErrorStatus.INVALID_MEMBER_ID;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final CustomMemberRepository customMemberRepository;
    private final AuthTokensGenerator authTokensGenerator;

    public Member findById(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new GeneralException(INVALID_MEMBER_ID));
    }

    @Transactional
    public void saveContentPreference(final PreferenceUploadRequest preferenceRequest) {
        final Member loginMember = authTokensGenerator.getLoginMember();
        loginMember.modifyPreference(preferenceRequest.getPreferenceList());
    }

    public PreferenceResponse getContentPreference() {
        final Member loginMember = authTokensGenerator.getLoginMember();
        final List<String> preferenceList = customMemberRepository.findPreferencesByMember(loginMember.getId());
        return new PreferenceResponse(
                loginMember.getName(),
                preferenceList
        );
    }
}
