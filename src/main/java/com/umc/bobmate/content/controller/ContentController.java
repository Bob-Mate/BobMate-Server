package com.umc.bobmate.content.controller;

import com.umc.bobmate.content.domain.Content;
import com.umc.bobmate.content.dto.ContentResponse;
import com.umc.bobmate.content.dto.ContentSpecialResponse;
import com.umc.bobmate.content.service.ContentService;
import static com.umc.bobmate.global.apiPayload.code.status.ErrorStatus._BAD_REQUEST;
import static com.umc.bobmate.global.apiPayload.code.status.SuccessStatus._OK;

import com.umc.bobmate.global.apiPayload.exception.GeneralException;
import com.umc.bobmate.like.service.LikeService;
import com.umc.bobmate.login.jwt.util.AuthTokensGenerator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.umc.bobmate.content.domain.ContentType;
import com.umc.bobmate.global.apiPayload.ApiResponse;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/contents")
@RequiredArgsConstructor
@Tag(name = "콘텐츠 API", description = "Best3 콘텐츠 API")
public class ContentController {

    private final ContentService contentService;
    private final AuthTokensGenerator authTokensGenerator;
    private final LikeService likeService;

    @GetMapping("/top3")
    @Operation(summary = "영상 best3, 텍스트 best3", description = "파라미터로 받은 section을 확인하여 해당 콘텐츠를 반환합니다.")
    @Parameter(name = "section", description = "section 0이면 영상, 1이면 텍스트")
    public ApiResponse<List<ContentResponse>> getTop3Contents(@RequestParam(name = "section") int section) {

        if (section == 0) { // 0: VIDEO
            return ApiResponse.onSuccess(contentService.getTop3ContentsByLikes(ContentType.VIDEO));
        } else if (section == 1) { // 1: TEXT
            return ApiResponse.onSuccess(contentService.getTop3ContentsByLikes(ContentType.TEXT));
        } else {
            throw new GeneralException(_BAD_REQUEST);
        }
    }


    @PostMapping("/like")
    @Operation(summary = "콘텐츠 찜 누르기", description = "Authorization 헤더 필요", security = @SecurityRequirement(name = "Authorization"))
    @Parameter(name = "contentId", description = "콘텐츠 ID")
    public ApiResponse<Void> likeContent(@RequestParam("contentId") Long contentId) {
        try {
            Long memberId = authTokensGenerator.getLoginMemberId();
            likeService.likeContent(memberId, contentId);
            return ApiResponse.of(_OK);
        } catch (Exception e) {
            throw new GeneralException(_BAD_REQUEST);
        }
    }

    @PostMapping("/unlike")
    @Operation(summary = "콘텐츠 찜 취소하기", description = "Authorization 헤더 필요", security = @SecurityRequirement(name = "Authorization"))
    @Parameter(name = "contentId", description = "콘텐츠 ID")
    public ApiResponse<Void> unlikeContent(@RequestParam("contentId") Long contentId) {
        try {
            Long memberId = authTokensGenerator.getLoginMemberId();
            likeService.unlikeContent(memberId, contentId);
            return ApiResponse.of(_OK);
        } catch (Exception e) {
            throw new GeneralException(_BAD_REQUEST);
        }

    }


    // 일반상황으로 컨텐츠 추천
    @GetMapping("/recommend/daily")
    public ApiResponse<List<ContentResponse>> recommendContent(@RequestParam String emotion,
                                                               @RequestParam String withWhom,
                                                               @RequestParam ContentType contentType) {
        System.out.println("start checking daily");

        // 여기서 사용자의 선택에 따른 추천 컨텐츠를 가져오기
        try {
            List<Content> recommendedContents = contentService.recommendContents(emotion, withWhom, contentType);

            // ContentResponseDTO로 변환
            List<ContentResponse> contentDailyResponseList = recommendedContents.stream()
                    .map(content -> ContentResponse.builder()
                            .contentId(content.getId())
                            .name(content.getName())
                            .imgUrl(content.getImgUrl())
                            .linkUrl(content.getLinkUrl())
                            .build())
                    .collect(Collectors.toList());

            return ApiResponse.onSuccess(contentDailyResponseList);
        } catch (Exception e) {
            throw new GeneralException(_BAD_REQUEST);
        }
    }


    @GetMapping("/recommend/special/{commentId}")
    public ApiResponse<List<ContentSpecialResponse>> recommendSpecialContent(@PathVariable Long commentId,
                                                                             @RequestParam ContentType type) {

        try {
            return ApiResponse.onSuccess(contentService.findContentWithFrequentGenre(commentId, type));
        }
        catch (Exception e) {
            throw new GeneralException(_BAD_REQUEST);
        }
    }
}

