package com.umc.bobmate.content.controller;

import com.umc.bobmate.content.domain.Content;
import com.umc.bobmate.content.domain.Emotion;
import com.umc.bobmate.content.dto.ContentRequestDTO;
import com.umc.bobmate.content.dto.ContentResponseDTO;
import com.umc.bobmate.content.service.ContentService;
import java.util.List;
import java.util.stream.Collectors;

import com.umc.bobmate.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.umc.bobmate.content.domain.ContentType;
import com.umc.bobmate.global.apiPayload.ApiResponse;

@RestController
@RequestMapping("/api/v1/contents")
@RequiredArgsConstructor
public class ContentController {
    private final ContentService contentService;
    private final LikeService likeService;

//    @GetMapping("")
//    public ApiResponse<List<ContentResponseDTO>> getAllContents(@RequestParam(name = "section") int section) {
//
//        if (section == 0) { // 0: VIDEO
//            return ApiResponse.onSuccess(contentService.getTop3Contents(ContentType.VIDEO));
//        } else if (section == 1) { // 1: TEXT
//            return ApiResponse.onSuccess(contentService.getTop3Contents(ContentType.TEXT));
//        } else {
//            System.out.println("섹션값 오류");
//            throw new IllegalArgumentException("Invalid");
//        }
//    }


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

/*    @PostMapping("/recommend")
    public ApiResponse<List<ContentResponseDTO>> recommendContent(@RequestBody ContentRequestDTO contentRequestDTO) {
        String emotion = contentRequestDTO.getEmotion();
        String withWhom = contentRequestDTO.getWithWhom();
        String contentType = contentRequestDTO.getContentType();

        // 여기서 사용자의 선택에 따른 추천 컨텐츠를 가져오기
        List<Content> recommendedContents = contentService.recommendContents(emotion, withWhom, contentType);
        recommendedContents.stream().toArray();
        return null;
    }
*/

    @PostMapping("/recommend")
    public ApiResponse<List<ContentResponseDTO>> recommendContent(@RequestBody ContentRequestDTO contentRequestDTO) {
        String emotion = contentRequestDTO.getEmotion();
        String withWhom = contentRequestDTO.getWithWhom();
        String contentType = contentRequestDTO.getContentType();

        // 여기서 사용자의 선택에 따른 추천 컨텐츠를 가져오기
        List<Content> recommendedContents = contentService.recommendContents(emotion, withWhom, contentType);

        // ContentResponseDTO로 변환
        List<ContentResponseDTO> contentResponseDTOList = recommendedContents.stream()
                .map(content -> ContentResponseDTO.builder()
                        .contentId(content.getId())
                        .name(content.getName())
                        .imgUrl(content.getImgUrl())
                        .linkUrl(content.getLinkUrl())
                        .build())
                .collect(Collectors.toList());

        // ApiResponse를 이용하여 응답 생성 및 반환
        return ApiResponse.onSuccess(contentResponseDTOList);
    }

}
