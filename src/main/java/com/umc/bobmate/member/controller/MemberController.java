package com.umc.bobmate.member.controller;

import com.umc.bobmate.global.apiPayload.ApiResponse;
import com.umc.bobmate.member.dto.request.PreferenceUploadRequest;
import com.umc.bobmate.member.dto.response.PreferenceResponse;
import com.umc.bobmate.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/api/v1/members/preference")
    public ApiResponse<Void> saveContentPreference(@RequestBody final PreferenceUploadRequest preferenceRequest) {
        memberService.saveContentPreference(preferenceRequest);
        return ApiResponse.onSuccess();
    }

    @GetMapping("/api/v1/members/preference")
    public ApiResponse<PreferenceResponse> getContentPreference() {
        return ApiResponse.onSuccess(memberService.getContentPreference());
    }
}
