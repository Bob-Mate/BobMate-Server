package com.umc.bobmate.content.controller;

import com.umc.bobmate.comment.dto.CommentResponseDTO;
import com.umc.bobmate.content.domain.Content;
import com.umc.bobmate.content.domain.Emotion;
import com.umc.bobmate.content.domain.Genre;
import com.umc.bobmate.content.domain.repository.ContentRepository;
import com.umc.bobmate.content.dto.ContentRequestDTO;
import com.umc.bobmate.content.dto.ContentResponseDTO;
import com.umc.bobmate.content.dto.ContentSpecialSituationResponse;
import com.umc.bobmate.content.service.ContentService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.umc.bobmate.global.apiPayload.exception.GeneralException;
import com.umc.bobmate.like.service.LikeService;
import com.umc.bobmate.login.jwt.util.AuthTokensGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.umc.bobmate.content.domain.ContentType;
import com.umc.bobmate.global.apiPayload.ApiResponse;

import static com.umc.bobmate.global.apiPayload.code.status.ErrorStatus._BAD_REQUEST;
import static com.umc.bobmate.global.apiPayload.code.status.SuccessStatus._OK;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/contents")
@RequiredArgsConstructor
public class ContentController {
    private final ContentService contentService;
    private final ContentService commentService;
    private final ContentRepository contentRepository;
    private final AuthTokensGenerator authTokensGenerator;

    // 일반상황으로 컨텐츠 추천
    @GetMapping("/recommend/daily")
    public ApiResponse<List<ContentResponseDTO>> recommendContent(@RequestParam String emotion,
                                                                  @RequestParam String withWhom,
                                                                  @RequestParam ContentType contentType) {

        // 여기서 사용자의 선택에 따른 추천 컨텐츠를 가져오기
        try {
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

            return ApiResponse.onSuccess(contentResponseDTOList);
        } catch (Exception e) {
            throw new GeneralException(_BAD_REQUEST);
        }
    }

    // 특별상황으로 컨텐츠 추천
    @GetMapping("/recommend/special")
    public ApiResponse<List<ContentResponseDTO>> recommendByGenre(
            CommentResponseDTO dto,
            @RequestParam ContentType type) {
        // 주어진 감정과 음식으로 가장 많이 등장한 장르 추출

        try{
            List<Content> recommendedContents = contentService.findSpecialContent(dto, type);

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

            // 추천된 컨텐츠를 반환합니다.
            return ApiResponse.onSuccess(contentResponseDTOList);
        }  catch (Exception e) {
            throw new GeneralException(_BAD_REQUEST);
        }


    }
}

//        } else {
//            return ApiResponse.onFailure(HttpStatus.NOT_FOUND,
//                    "No recommendations found for the given emotion and food.")
//
//        }

//    private List<Content> getContentByGenre(Genre genre, ContentType type) {
//        // 여기에 해당 장르의 컨텐츠를 가져오는 로직을 추가합니다.
//        List<Content> content = ;
//
//
//        return contentService.findContentsByGenre(genre);
//    }
//}


//    @GetMapping("/recommend/special")
//    public ApiResponse<List<ContentResponseDTO>> recommendContentsSpecial(Genre genre, @RequestParam ContentType contentType) {
//        List<Content> contents = contentService.recommendSpecialSituation(genre, contentType);
//
//        List<ContentResponseDTO> contentResponseDTOList = responseList.stream()
//                .map(content -> ContentResponseDTO.builder()
//                        .contentId(content.getId())
//                        .name(content.getName())
//                        .imgUrl(content.getImgUrl())
//                        .linkUrl(content.getLinkUrl())
//                        .type(content.getType())
//                        .genreList(content.getGenreList())
//                        .emotionList(content.getEmotionList())
//                        .build())
//                .collect(Collectors.toList());
//
//        return ApiResponse.onSuccess(contentResponseDTOList);
//    }

//    @GetMapping("/recommend/special")
//    public ApiResponse<List<Content>> recommendBySituation(@RequestParam String genre){
//
//    }