package com.umc.bobmate.menu.controller;

import static com.umc.bobmate.global.apiPayload.code.status.ErrorStatus._BAD_REQUEST;
import static com.umc.bobmate.global.apiPayload.code.status.SuccessStatus._OK;

import com.umc.bobmate.global.apiPayload.ApiResponse;
import com.umc.bobmate.global.apiPayload.exception.GeneralException;
import com.umc.bobmate.like.service.LikeService;
import com.umc.bobmate.login.jwt.util.AuthTokensGenerator;
import com.umc.bobmate.menu.dto.MenuResponse;
import com.umc.bobmate.menu.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/menus")
@RequiredArgsConstructor
@Tag(name = "메뉴 추천", description = "아침/점심/저녁/야식 랜덤추천 API")
public class MenuController {
    private final MenuService menuService;
    private final LikeService likeService;
    private final AuthTokensGenerator authTokensGenerator;

    @GetMapping("")
    @Operation(summary = "메뉴 랜덤 추천", description = "랜덤하게 메뉴를 추천합니다.")
    public ApiResponse<List<MenuResponse>> getRandomMenus() {
        return ApiResponse.onSuccess(menuService.getRandomMenus());
    }

    @PostMapping("/like")
    @Operation(summary = "메뉴 찜 누르기", description = "Authorization 헤더 필요", security = @SecurityRequirement(name = "Authorization"))
    @Parameter(name = "menuId", description = "메뉴 ID")
    public ApiResponse<Void> likeMenu(@RequestParam("menuId") Long menuId) {
        try {
            Long memberId = authTokensGenerator.getLoginMemberId();
            likeService.likeMenu(memberId, menuId);
            return ApiResponse.of(_OK);
        } catch (Exception e) {
            throw new GeneralException(_BAD_REQUEST);
        }
    }

    @PostMapping("/unlike")
    @Operation(summary = "메뉴 찜 취소", description = "Authorization 헤더 필요", security = @SecurityRequirement(name = "Authorization"))
    @Parameter(name = "menuId", description = "메뉴 ID")
    public ApiResponse<Void> unlikeMenu(@RequestParam("menuId") Long menuId) {
        try {
            Long memberId = authTokensGenerator.getLoginMemberId();
            likeService.unlikeMenu(memberId, menuId);
            return ApiResponse.of(_OK);
        } catch (Exception e) {
            throw new GeneralException(_BAD_REQUEST);
        }
    }

}