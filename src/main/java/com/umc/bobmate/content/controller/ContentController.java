package com.umc.bobmate.content.controller;

import com.umc.bobmate.content.domain.Content;
import com.umc.bobmate.content.domain.Emotion;
import com.umc.bobmate.content.dto.ContentRequestDTO;
import com.umc.bobmate.content.dto.ContentResponseDTO;
import com.umc.bobmate.content.dto.ContentSpecialSituationResponse;
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

    @GetMapping("/recommend/daily")
    public ApiResponse<List<ContentResponseDTO>> recommendContent(@RequestParam String emotion,
                                                                  @RequestParam String withWhom,
                                                                  @RequestParam ContentType contentType) {
        // 여기서 사용자의 선택에 따른 추천 컨텐츠를 가져오기
        List<Content> recommendedContents = contentService.recommendContents(emotion, withWhom, contentType);

        // ContentResponseDTO로 변환
        List<ContentResponseDTO> contentResponseDTOList = recommendedContents.stream()
                .map(content -> ContentResponseDTO.builder()
                        .contentId(content.getId())
                        .name(content.getName())
                        .imgUrl(content.getImgUrl())
                        .linkUrl(content.getLinkUrl())
                        .type(content.getType())
                        .genreList(content.getGenreList())
                        .emotionList(content.getEmotionList())
                        .build())
                .collect(Collectors.toList());

        // ApiResponse를 이용하여 응답 생성 및 반환
        return ApiResponse.onSuccess(contentResponseDTOList);
    }

    @GetMapping("/recommend/special")
    public ApiResponse<List<ContentResponseDTO>> recommendContentsSpecial(@RequestParam Integer id,
                                                                          @RequestParam ContentType contentType) {
        List<Content> responseList = contentService.recommendSpecialSituation(id, contentType);

        List<ContentResponseDTO> contentResponseDTOList = responseList.stream()
                .map(content -> ContentResponseDTO.builder()
                        .contentId(content.getId())
                        .name(content.getName())
                        .imgUrl(content.getImgUrl())
                        .linkUrl(content.getLinkUrl())
                        .type(content.getType())
                        .genreList(content.getGenreList())
                        .emotionList(content.getEmotionList())
                        .build())
                .collect(Collectors.toList());

        return ApiResponse.onSuccess(contentResponseDTOList);
    }

}
