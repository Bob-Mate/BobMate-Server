package com.umc.bobmate.login.controller;

import com.umc.bobmate.global.apiPayload.ApiResponse;
import com.umc.bobmate.login.jwt.token.AuthTokens;
import com.umc.bobmate.login.oauth.dto.param.KakaoLoginParams;
import com.umc.bobmate.login.oauth.dto.param.NaverLoginParams;
import com.umc.bobmate.login.service.OAuthLoginService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.umc.bobmate.member.domain.OAuthProvider.KAKAO;
import static com.umc.bobmate.member.domain.OAuthProvider.NAVER;

@Hidden
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final OAuthLoginService oAuthLoginService;

    @PostMapping("/kakao")
    public ApiResponse<AuthTokens> loginKakao(@RequestBody KakaoLoginParams params) {
        return ApiResponse.onSuccess(oAuthLoginService.login(params));
    }

    @PostMapping("/naver")
    public ApiResponse<AuthTokens> loginNaver(@RequestBody NaverLoginParams params) {
        return ApiResponse.onSuccess(oAuthLoginService.login(params));
    }

    @DeleteMapping("/kakao")
    public ApiResponse<Void> unlinkKakao() {
        oAuthLoginService.unlink(KAKAO);
        return ApiResponse.onSuccess();
    }

    @DeleteMapping("/naver")
    public ApiResponse<Void> unlinkNaver() {
        oAuthLoginService.unlink(NAVER);
        return ApiResponse.onSuccess();
    }
}
