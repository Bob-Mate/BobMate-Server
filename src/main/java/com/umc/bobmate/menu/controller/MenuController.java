package com.umc.bobmate.menu.controller;

import static com.umc.bobmate.global.apiPayload.code.status.SuccessStatus._OK;

import com.umc.bobmate.global.apiPayload.ApiResponse;
import com.umc.bobmate.like.service.LikeService;
import com.umc.bobmate.menu.dto.MenuResponse;
import com.umc.bobmate.menu.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/menus")
@RequiredArgsConstructor
@Tag(name = "메뉴 추천", description = "아침/점심/저녁/야식 랜덤추천 API")
public class MenuController {
    private final MenuService menuService;
    private final LikeService likeService;

    @GetMapping("")
    @Operation(summary = "메뉴 랜덤 추천", description = "랜덤하게 메뉴를 추천합니다.")
    public ApiResponse<List<MenuResponse>> getRandomMenus() {
        return ApiResponse.onSuccess(menuService.getRandomMenus());
    }

    @PostMapping("/like")
    @Operation(summary = "메뉴 찜 누르기", description = "메뉴에 찜을 누릅니다.")
    @Parameter(name = "memberId", description = "회원 ID")
    @Parameter(name = "menuId", description = "메뉴 ID")
    public ApiResponse<Void> likeMenu(@RequestParam("memberId") Long memberId, @RequestParam("menuId") Long menuId) {
        likeService.likeMenu(memberId, menuId);
        return ApiResponse.of(_OK);
    }

    @PostMapping("/unlike")
    @Operation(summary = "메뉴 찜 취소", description = "메뉴에 찜을 취소합니다.")
    @Parameter(name = "memberId", description = "회원 ID")
    @Parameter(name = "menuId", description = "메뉴 ID")
    public ApiResponse<Void> unlikeContent(@RequestParam("memberId") Long memberId, @RequestParam("menuId") Long menuId) {
        likeService.unlikeMenu(memberId, menuId);
        return ApiResponse.of(_OK);
    }

}