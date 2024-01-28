package com.umc.bobmate.content.controller;

import com.umc.bobmate.content.domain.Content;
import com.umc.bobmate.content.dto.ContentRequestDTO;
import com.umc.bobmate.content.dto.ContentResponseDTO;
import com.umc.bobmate.content.service.ContentService;
import java.util.List;

import com.umc.bobmate.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.umc.bobmate.content.domain.ContentType;
import com.umc.bobmate.global.apiPayload.ApiResponse;

@RestController
@RequestMapping("/api/v1/contents")
@RequiredArgsConstructor
public class ContentController {
    private final ContentService contentService;
    private final LikeService likeService;

    @GetMapping("")
    public ApiResponse<List<ContentResponseDTO>> getAllContents(@RequestParam(name = "section") int section) {

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
    public List<ContentResponseDTO> recommendContents(ContentRequestDTO dto){
        String contentAge = dto.getWithWhom();
        String type = dto.getContentType();
        String emotion = dto.getEmotion();
        return null;
    }

    @PostMapping("/recommend")
    public List<Content> recommendContent(@RequestBody ContentRequestDTO contentRequestDTO) {
        String emotion = contentRequestDTO.getEmotion();
        String withWhom = contentRequestDTO.getWithWhom();
        String contentType = contentRequestDTO.getContentType();

        // 여기서 사용자의 선택에 따른 추천 컨텐츠를 가져오기
        List<Content> recommendedContents = contentService.recommendContents(emotion, withWhom, contentType);

        // 추천 컨텐츠의 emotion 리스트 접근
        for (Content content : recommendedContents) {
            List<String> emotionList = content.getEmotionList();
            // 여기서 emotionList 활용
        }

        // 프론트에 추천 컨텐츠 반환
        return recommendedContents;
    }



//        return contentService.recommendContents(dto);


}
