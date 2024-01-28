package com.umc.bobmate.content.service;

import com.umc.bobmate.content.domain.Content;
import com.umc.bobmate.content.domain.ContentType;
import com.umc.bobmate.content.dto.ContentRequestDTO;
import com.umc.bobmate.content.dto.ContentResponseDTO;
import com.umc.bobmate.content.domain.repository.ContentRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class ContentService {
    private final ContentRepository contentRepository;



    public List<Content> recommendContents(String emotion, String withWhom, String contentType) {
        String contentTypeEnum = String.valueOf(ContentType.valueOf(contentType.toUpperCase()));
        List<Content> recommendedContents = contentRepository.findByType(contentTypeEnum);

        List<Content> recommend = new ArrayList<>();
        for (Content content : recommendedContents) {
            List<String> emotionList = content.getEmotionList();
            if (emotionList.contains(emotion)) {
                // 감정에 해당하는 경우에만 추가
                switch (emotion) {
                    case "GLAD":
                        if (content.getGenreList().contains("COMEDY") || content.getGenreList().contains("ANIMATION") || content.getGenreList().contains("ROMANCE")) {
                            recommend.add(content);
                        }
                        break;

                    case "EXCITED":
                        if (content.getGenreList().contains("ACTION") || content.getGenreList().contains("ROMANTIC_COMEDY") || content.getGenreList().contains("ROMANCE")) {
                            recommend.add(content);
                        }
                        break;

                    case "GLOOMY":
                        if (content.getGenreList().contains("DRAMA") || content.getGenreList().contains("ACTION") || content.getGenreList().contains("ANIMATION")) {
                            recommend.add(content);
                        }
                        break;

                    case "ANGRY":
                        if (content.getGenreList().contains("ACTION") || content.getGenreList().contains("THRILLER") || content.getGenreList().contains("CRIME")) {
                            recommend.add(content);
                        }
                        break;

                    case "SAD":
                        if (content.getGenreList().contains("DRAMA") || content.getGenreList().contains("ROMANCE") || content.getGenreList().contains("FAMILY")) {
                            recommend.add(content);
                        }
                        break;
                }
            }
        }
        return recommend;
    }




/*    private final ContentRepository contentRepository;

    public List<ContentResponseDTO> getTop3Contents(ContentType contentType) {
        return contentRepository.findTop3ContentsByLikesAndType(contentType).stream()
                .map(content -> ContentResponseDTO.builder().contentId(content.getId()).name(content.getName())
                        .imgUrl(content.getImgUrl()).linkUrl(content.getLinkUrl()).build())
                .collect(Collectors.toList());
    }

    public List<Content> recommendContents(String emotion, String withWhom, String contentType) {
        ContentType contentTypeEnum = ContentType.valueOf(contentType.toUpperCase());
        List<Content> recommendedContents = contentRepository.findByType(contentType);

        List<Content> recommend = new ArrayList<>();
        for (Content content : recommendedContents) {
            List<String> emotionList = content.getEmotionList();
            for (String e : emotionList){
                switch (e){
                    //GLAD, EXCITED, GLOOMY, ANGRY, SAD;
                    case "GLAD":
                        if (content.getGenreList().contains("COMEDY") || content.getGenreList().contains("ANIMATION") || content.getGenreList().contains("ROMANCE")) {
                            recommend.add(content);
                        }
                        break;

                    case "EXCITED" :
                        if (content.getGenreList().contains("ACTION") || content.getGenreList().contains("ACTION") || content.getGenreList().contains("ROMANCE")){
                            recommend.add(content);
                        } break;

                    case "GLOOMY" :
                    if (content.getGenreList().contains("DRAMA") || content.getGenreList().contains("ACTION") || content.getGenreList().contains("ANIMATION")){
                        recommend.add(content);
                    }break;

                    case "ANGRY" :
                        if (content.getGenreList().contains("ACTION") || content.getGenreList().contains("THRILLER") || content.getGenreList().contains("CRIME")){
                            recommend.add(content);
                        } break;

                    case "SAD" :
                        if (content.getGenreList().contains("DRAMA") || content.getGenreList().contains("Romance") || content.getGenreList().contains("FAMILY")){
                            recommend.add(content);
                        } break;

                }
            }

        }
        return recommend;
    } */


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