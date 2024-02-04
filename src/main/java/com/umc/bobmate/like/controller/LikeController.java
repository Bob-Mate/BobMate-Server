package com.umc.bobmate.like.controller;

import static com.umc.bobmate.global.apiPayload.code.status.ErrorStatus._BAD_REQUEST;

import com.umc.bobmate.content.dto.ContentResponse;
import com.umc.bobmate.global.apiPayload.ApiResponse;
import com.umc.bobmate.global.apiPayload.exception.GeneralException;
import com.umc.bobmate.like.service.LikeService;
import com.umc.bobmate.login.jwt.util.AuthTokensGenerator;
import com.umc.bobmate.menu.dto.MenuResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/likes")
@RequiredArgsConstructor
@Tag(name = "나의 찜", description = "사용자가 찜한 콘텐츠/메뉴 조회 API")
public class LikeController {
    private final LikeService likeService;
    private final AuthTokensGenerator authTokensGenerator;

    @GetMapping("/content")
    @Operation(summary = "내가 찜한 콘텐츠", description = "Authorization 헤더 필요", security = @SecurityRequirement(name = "Authorization"))
    public ApiResponse<List<ContentResponse>> getLikedContents() {
        try {
            Long memberId = authTokensGenerator.getLoginMemberId();
            List<ContentResponse> likedContents = likeService.getLikedContents(memberId);
            return ApiResponse.onSuccess(likedContents);
        } catch (Exception e) {
            e.printStackTrace(); // 예외 정보 출력
            throw new GeneralException(_BAD_REQUEST);
        }
    }

    @GetMapping("/menu")
    @Operation(summary = "내가 찜한 메뉴", description = "Authorization 헤더 필요", security = @SecurityRequirement(name = "Authorization"))
    public ApiResponse<List<MenuResponse>> getLikedMenus() {
        try {
            Long memberId = authTokensGenerator.getLoginMemberId();
            List<MenuResponse> likedMenus = likeService.getLikedMenus(memberId);
            return ApiResponse.onSuccess(likedMenus);
        } catch (Exception e) {
            e.printStackTrace(); // 예외 정보 출력
            throw new GeneralException(_BAD_REQUEST);
        }
    }

}
