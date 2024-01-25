package com.umc.bobmate.content.controller;

import com.umc.bobmate.content.domain.Emotion;
import com.umc.bobmate.content.dto.ContentRequest;
import com.umc.bobmate.content.dto.ContentResponse;
import com.umc.bobmate.content.service.ContentService;
import java.util.List;

import com.umc.bobmate.like.dto.LikesDto;
import com.umc.bobmate.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umc.bobmate.content.domain.ContentType;
import com.umc.bobmate.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/contents")
@RequiredArgsConstructor
public class ContentController {
    private final ContentService contentService;

    @GetMapping("")
    public ApiResponse<List<ContentResponse>> getAllContents(@RequestParam(name = "section") int section) {

        if (section == 0) { // 0: VIDEO
            return ApiResponse.onSuccess(contentService.getTop3Contents(ContentType.VIDEO));
        } else if (section == 1) { // 1: TEXT
            return ApiResponse.onSuccess(contentService.getTop3Contents(ContentType.TEXT));
        } else {
            System.out.println("섹션값 오류");
            throw new IllegalArgumentException("Invalid");
        }
    }


    //    @GetMapping("/video")
    //    public ApiResponse<List<ContentResponse>> getTop3VideoContents() {
    //        return ApiResponse.onSuccess(contentService.getAllVideoContents());
    //    }
    //
    //    @GetMapping("/text")
    //    public ApiResponse<List<ContentResponse>> getTop3TextContents() {
    //        return ApiResponse.onSuccess(contentService.getAllTextContents());
    //    }

    /* 컨텐츠 추천 결과 */
    @GetMapping("/recommend/result")
    public List<ContentResponse> recommendContents(ContentRequest dto){
        String contentAge = dto.getWithWhom();
        String type = dto.getContentType();
        String emotion = dto.getEmotion();
        return null;
        }


//        return contentService.recommendContents(dto);
    /* 컨텐츠 찜 목록 */
    @GetMapping("/like")
    public List<LikesDto> findLikePosts(@RequestParam("memberId") String memberId) {
//        Long l = Long.parseLong(memberId);
//        // memberId에 해당하는 Member를 찾음
//        Member member = memberService.findById(l);
//
//        if (member != null) {
//            // LikeHistoryRepository를 통해 해당 Member가 좋아요한 모든 게시물 조회
//            return likeHistoryService.findByMemberLike(member).stream().map(LikePostDto::new).collect(Collectors.toList());
//
//        }
        return null;
    }

}
