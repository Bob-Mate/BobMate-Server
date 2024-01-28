package com.umc.bobmate.member.controller;

import com.umc.bobmate.global.apiPayload.ApiResponse;
import com.umc.bobmate.member.dto.request.CommentUploadRequest;
import com.umc.bobmate.member.dto.request.EditRequest;
import com.umc.bobmate.member.dto.request.PreferenceUploadRequest;
import com.umc.bobmate.member.dto.response.CommentResponse;
import com.umc.bobmate.member.dto.response.EditPageResponse;
import com.umc.bobmate.member.dto.response.PreferenceResponse;
import com.umc.bobmate.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/api/v1/members/edit")
    public ApiResponse<EditPageResponse> getEditPage() {
        return ApiResponse.onSuccess(memberService.getEditPage());
    }

    @PostMapping(value = "/api/v1/members/edit", consumes = {APPLICATION_JSON_VALUE, MULTIPART_FORM_DATA_VALUE})
    public ApiResponse<Void> getEditPage(
            @RequestPart("profileImage") MultipartFile multipartFile,
            @RequestPart("request") EditRequest editRequest
    ) {
        memberService.editProfile(multipartFile, editRequest);
        return ApiResponse.onSuccess();
    }

    @PostMapping("/api/v1/members/preference")
    public ApiResponse<Void> saveContentPreference(@RequestBody final PreferenceUploadRequest preferenceRequest) {
        memberService.saveContentPreference(preferenceRequest);
        return ApiResponse.onSuccess();
    }

    @GetMapping("/api/v1/members/preference")
    public ApiResponse<PreferenceResponse> getContentPreference() {
        return ApiResponse.onSuccess(memberService.getContentPreference());
    }

    @PostMapping("/api/v1/members/comment")
    public ApiResponse<Void> saveComment(@RequestBody final CommentUploadRequest uploadRequest) {
        memberService.saveComment(uploadRequest);
        return ApiResponse.onSuccess();
    }

    @GetMapping("/api/v1/members/comment")
    public ApiResponse<CommentResponse> getComment() {
        return ApiResponse.onSuccess(memberService.getComment());
    }
}
