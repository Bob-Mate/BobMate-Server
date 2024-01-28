package com.umc.bobmate.like.controller;

import com.umc.bobmate.global.apiPayload.ApiResponse;
import com.umc.bobmate.like.domain.Likes;
import com.umc.bobmate.like.domain.repository.LikeRepository;
import com.umc.bobmate.like.dto.LikesDto;
import com.umc.bobmate.like.service.LikeService;
import com.umc.bobmate.member.domain.Member;
import com.umc.bobmate.menu.domain.repository.MenuRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class LikeController {
    private final LikeService likeService;
    private final LikeRepository likeRepository;
    private final MenuRepository menuRepository;

    private Member member;










}

//    @PostMapping("/api/store/likes")
//    public ResponseEntity<String> likes(long id, String likes, @AuthenticationPrincipal CustomUserDetails principal) {
//
//        if(principal == null) {
//            return ResponseEntity.badRequest().body("회원만 가능합니다");
//        }
//
//        long userId = member.getId();
//        storeService.likes(id, likes, userId);
//        return ResponseEntity.ok().body("완료");
//    }

    /* 컨텐츠 찜 목록 */
//    @GetMapping("/like")
//    public List<LikesRe> findLikePosts(@RequestParam("memberId") String memberId) {
//        Long l = Long.parseLong(memberId);
//        // memberId에 해당하는 Member를 찾음
//        Member member = memberService.findById(l);
//
//        if (member != null) {
//            // LikeHistoryRepository를 통해 해당 Member가 좋아요한 모든 게시물 조회
//            return likeHistoryService.findByMemberLike(member).stream().map(LikePostDto::new).collect(Collectors.toList());
//
//        }
//        return null;
//    }

//    @GetMapping("/my/menu")
//    public ApiResponse<List<Likes>> getMyMenuLikes(@RequestParam Long memberId) {
//        List<Likes> menuLikes = likeService.findMenuLikesByMemberId(memberId);
//        return ApiResponse.onSuccess(menuLikes);
//    }
//
//    @GetMapping("/my/menu")
//    public ApiResponse<List<MenuResponseDTO>> getMyMenuLikes(@RequestParam Long memberId) {
//        List<Likes> menuLikes = likeService.findMenuLikesByMemberId(memberId);
//        List<MenuResponseDTO> menuResponseDTOs = menuLikes.stream()
//                .map(MenuConverter::toMenuResponseDTO)
//                .toList();
//        return ApiResponse.onSuccess(menuResponseDTOs, "Menu Likes retrieved successfully");
//    }
//
//    @GetMapping("/my/content")
//    public ApiResponse<List<Likes>> getMyContentLikes(@RequestParam Long memberId) {
//        List<Likes> contentLikes = likeService.findContentLikesByMemberId(memberId);
//        return ApiResponse.onSuccess(contentLikes);
//    }
//
//    @PostMapping("/add")
//    public ResponseEntity<ApiResponse<Likes>> addLike(@RequestBody Likes like) {
//        Likes addedLike = likeService.addLike(like);
//        return ApiResponse.created(addedLike, "Like added successfully");
//    }
//
//    @DeleteMapping("/remove/{likeId}")
//    public ResponseEntity<ApiResponse<Void>> removeLike(@PathVariable Long likeId) {
//        likeService.removeLike(likeId);
//        return ApiResponse.onSuccess(null);
//    }



