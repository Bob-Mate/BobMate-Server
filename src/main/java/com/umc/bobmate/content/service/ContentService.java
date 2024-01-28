package com.umc.bobmate.content.service;

import com.umc.bobmate.content.domain.Content;
import com.umc.bobmate.content.domain.ContentType;
import com.umc.bobmate.content.dto.ContentRequestDTO;
import com.umc.bobmate.content.dto.ContentResponseDTO;
import com.umc.bobmate.content.domain.repository.ContentRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class ContentService {
    private final ContentRepository contentRepository;

    public List<ContentResponseDTO> getTop3Contents(ContentType contentType) {
        return contentRepository.findTop3ContentsByLikesAndType(contentType).stream()
                .map(content -> ContentResponseDTO.builder().contentId(content.getId()).name(content.getName())
                        .imgUrl(content.getImgUrl()).linkUrl(content.getLinkUrl()).build())
                .collect(Collectors.toList());
    }

    public List<Content> recommendContents(String emotion, String withWhom, String contentType) {
        ContentType contentTypeEnum = ContentType.valueOf(contentType.toUpperCase());
        List<Content> recommendedContents = contentRepository.findByTypeAndEmotion(contentTypeEnum, emotion);

        // DRAMA, MOVIE, ANIMATION, MYSTERY, COMIC,
        //    COMEDY, ROMANCE, ACTION, THRILLER, CRIME, FANTASY,
        //    HIGHTEEN, FAMILY;

//        GLAD, EXCITED, GLOOMY, ANGRY, SAD;

        if ("GLAD".equals(emotion)) {
            recommendedContents = contentRepository.findByTypeAndEmotion(contentTypeEnum, "GLAD");
        } else if ("joy".equals(emotion)) {
            recommendedContents = contentRepository.findByTypeAndEmotion(contentTypeEnum, "모험");
        } else if ("sad".equals(emotion)) {
            recommendedContents = contentRepository.findByTypeAndEmotion(contentTypeEnum, "드라마");
        } else {
            // 그 외의 감정에 대한 처리
            recommendedContents = contentRepository.findByTypeAndEmotion(contentTypeEnum, "기본 장르");
        }


        return recommendedContents;
    }


//    public List<ContentResponse> getAllVideoContents() {
//        List<Content> videoContents = contentRepository.findByType(ContentType.VIDEO);
//        return videoContents.stream()
//                .map((video) -> ContentResponse.builder().contentId(video.getId()).name(video.getName())
//                        .imgUrl(video.getImgUrl()).linkUrl(video.getLinkUrl()).build()).collect(Collectors.toList());
//    }
//
//    public List<ContentResponse> getAllTextContents() {
//        List<Content> textContents = contentRepository.findByType(ContentType.TEXT);
//        return textContents.stream()
//                .map((text) -> ContentResponse.builder().contentId(text.getId()).name(text.getName())
//                        .imgUrl(text.getImgUrl()).linkUrl(text.getLinkUrl()).build()).collect(Collectors.toList());
//    }

//    public List<ContentResponseDTO> recommendContents(ContentRequestDTO dto) {
//
//        contentRepository.findByEmotion(dto.getEmotion(), 3);
//
////        return recommendedContents.stream()
////                .map(this::convertToDTO)
////                .collect(Collectors.toList());
//
//        return null;
//    }


    private ContentResponseDTO convertToDTO(Content content) {
        return ContentResponseDTO.builder()
                .contentId(content.getId())
                .name(content.getName())
                .type(content.getType().name()) // Enum 타입을 문자열로 변환
                .genreList(content.getGenreList())
                .emotionList(content.getEmotionList())
                .imgUrl(content.getImgUrl())
                .linkUrl(content.getLinkUrl())
                .build();
    }

    public boolean matchesGenre(String selectedGenre, List<String> contentGenres) {
        return contentGenres.contains(selectedGenre);

    }
}

//        String genre = dto.getGenreList().;
//        List<String> emotion = dto.getEmotionList();
//        ContentType type = dto.getType();
//
//        List<Content> recommendedContents = contentRepository.
//                findRecommendContents(genre, emotion, type);

//    public List<ContentResponse> recommendContents(ContentRequest dto) {
//        // ContentRequestDTO에서 필요한 정보를 추출하여 서비스에 전달
//        // 추천 로직을 구현하고 결과를 ContentResponseDTO로 매핑
//        List<Content> recommendedContents = contentRepository.findByGenreListAndEmotionListAndType(
//                dto.getGenreList().get(), requestDTO.getEmotion(), requestDTO.getContentType()
//        );
//        return recommendContents.stream()
//                .map(this::converTODTO)
//                .collect(Collectors.toList());
//    }

/*
public List<ContentResponse> filterContents(ContentRequest request) {
        // Emotion을 통한 장르 필터링
        List<Content> filteredContents = contentRepository.findByEmotionInAndGenreListSize(request.getEmotion(), 3);

        // ContentType을 통한 필터링
        if (request.getContentType() != null) {
            ContentType contentType = ContentType.valueOf(request.getContentType().toUpperCase());
            filteredContents = contentRepository.findByTypeAndEmotionInAndGenreListSize(contentType, request.getEmotion(), 3);
        }

        // 누구와 보는지에 따른 연령 필터링
        if ("가족".equals(request.getWithWhom())) {
            filteredContents = contentRepository.findByWithWhom("12세");
        } else if ("친구".equals(request.getWithWhom()) || "연인".equals(request.getWithWhom())) {
            filteredContents = contentRepository.findByWithWhom("15세");
        }

        return convertToContentResponseList(filteredContents);
    }

    private List<ContentResponse> convertToContentResponseList(List<Content> contents) {
        // Content를 ContentResponse로 변환하는 로직 추가
    }
*/