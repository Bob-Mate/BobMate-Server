package com.umc.bobmate.like.controller;

import static com.umc.bobmate.global.apiPayload.code.status.ErrorStatus._BAD_REQUEST;

import com.umc.bobmate.content.dto.ContentResponse;
import com.umc.bobmate.global.apiPayload.ApiResponse;
import com.umc.bobmate.global.apiPayload.exception.GeneralException;
import com.umc.bobmate.like.service.LikeService;
import com.umc.bobmate.menu.dto.MenuResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/likes")
@RequiredArgsConstructor
@Tag(name = "나의 찜", description = "사용자가 찜한 콘텐츠/메뉴 조회 API")
public class LikeController {
    private final LikeService likeService;

    @GetMapping("/content/{memberId}")
    @Operation(summary = "내가 찜한 콘텐츠", description = "사용자가 찜을 누른 콘텐츠 목록을 조회합니다.")
    @Parameter(name = "memberId", description = "사용자 ID")
    public ApiResponse<List<ContentResponse>> getLikedContents(@PathVariable("memberId") Long memberId) {
        try {
            return ApiResponse.onSuccess(likeService.getLikedContents(memberId));
        } catch (Exception e) {
            throw new GeneralException(_BAD_REQUEST);
        }
    }

    @GetMapping("/menu/{memberId}")
    @Operation(summary = "내가 찜한 메뉴", description = "사용자가 찜을 누른 메뉴 목록을 조회합니다.")
    @Parameter(name = "memberId", description = "사용자 ID")
    public ApiResponse<List<MenuResponse>> getLikedMenus(@PathVariable("memberId") Long memberId) {
        try {
            return ApiResponse.onSuccess(likeService.getLikedMenus(memberId));
        } catch (Exception e) {
            throw new GeneralException(_BAD_REQUEST);
        }
    }

}
