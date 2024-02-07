package com.umc.bobmate.like.controller;

import static com.umc.bobmate.global.apiPayload.code.status.ErrorStatus._BAD_REQUEST;

import com.umc.bobmate.content.dto.ContentDailyResponse;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/likes")
@RequiredArgsConstructor

@Tag(name = "나의 찜", description = "사용자가 찜한 콘텐츠/메뉴 조회 API")
public class LikeController {
    private final LikeService likeService;
    private final AuthTokensGenerator authTokensGenerator;

    @GetMapping("/content")
    @Operation(summary = "내가 찜한 콘텐츠", description = "Authorization 헤더 필요", security = @SecurityRequirement(name = "Authorization"))
    public ApiResponse<List<ContentDailyResponse>> getLikedContents() {
        try {
            Long memberId = authTokensGenerator.getLoginMemberId();
            List<ContentResponse> likedContents = likeService.getLikedContents(memberId);
            return ApiResponse.onSuccess(likedContents);
        } catch (Exception e) {
            e.printStackTrace();
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
            e.printStackTrace();
            throw new GeneralException(_BAD_REQUEST);
        }
    }

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



