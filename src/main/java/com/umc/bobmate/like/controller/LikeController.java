package com.umc.bobmate.like.controller;

import com.umc.bobmate.like.service.LikeService;
import com.umc.bobmate.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;
    private Member member;

    @PostMapping("/api/store/likes")
    public ResponseEntity<String> likes(long id, String likes, @AuthenticationPrincipal CustomUserDetails principal) {

        if(principal == null) {
            return ResponseEntity.badRequest().body("회원만 가능합니다");
        }

        long userId = member.getId();
        storeService.likes(id, likes, userId);
        return ResponseEntity.ok().body("완료");
    }
}
