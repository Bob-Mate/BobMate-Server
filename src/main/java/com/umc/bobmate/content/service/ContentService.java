package com.umc.bobmate.content.service;

import com.umc.bobmate.content.domain.Content;
import com.umc.bobmate.content.domain.ContentType;
import com.umc.bobmate.content.domain.repository.ContentRepository;

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
import com.umc.bobmate.content.dto.ContentResponseDTO;
=======
import com.umc.bobmate.content.dto.ContentSpecialSituationResponse;
>>>>>>> d7101cb7d1b615c35946aacc24dab758ab3b4179
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class ContentService {
    private final ContentRepository contentRepository;

//    public List<ContentResponseDTO> getTop3Contents(ContentType contentType) {
//        return contentRepository.findTop3ContentsByLikesAndType(contentType).stream()
//                .map(content -> ContentResponseDTO.builder().contentId(content.getId()).name(content.getName())
//                        .imgUrl(content.getImgUrl()).linkUrl(content.getLinkUrl()).build())
//                .collect(Collectors.toList());
//    }

    @Transactional
    public List<Content> recommendContents(String emotion, String withWhom, ContentType contentType) {
        List<Content> recommendedContents = contentRepository.findByType(contentType);

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

    @Transactional
    public List<Content> recommendSpecialSituation(Integer id, ContentType type) {
        // id에 따른 특정 상황에 맞는 추천 컨텐츠를 찾기
        List<Content> recommendedContents = contentRepository.findByType(type);
        List<Content> recommend = new ArrayList<>();

        for(Content c : recommendedContents){
            switch (id) {
                case 1:
                    if (c.getGenreList().contains("DRAMA") || c.getGenreList().contains("ROMANCE"))
                        recommend.add(c);
                    break;

                case 2:
                    if (c.getGenreList().contains("COMEDY") || c.getGenreList().contains("HIGHTEEN"))
                        recommend.add(c);
<<<<<<< HEAD
                    break;
=======
                     break;
>>>>>>> d7101cb7d1b615c35946aacc24dab758ab3b4179

                case 3:
                    if (c.getGenreList().contains("CRIME") || c.getGenreList().contains("ACTION") || c.getGenreList().contains("FANTASY"))
                        recommend.add(c);
                    break;

                case 4:

                    if (c.getGenreList().contains("ANIMATION"))
<<<<<<< HEAD
                        recommend.add(c);
=======
                            recommend.add(c);
>>>>>>> d7101cb7d1b615c35946aacc24dab758ab3b4179

                    break;

                default:
                    throw new IllegalArgumentException("Invalid id: " + id);
            }

        }

        return recommend;
    }

<<<<<<< HEAD
    public ContentResponseDTO mapContentToResponseDTO(Content content) {
        return ContentResponseDTO.builder()
=======
    public ContentSpecialSituationResponse mapContentToResponseDTO(Content content) {
        return ContentSpecialSituationResponse.builder()
>>>>>>> d7101cb7d1b615c35946aacc24dab758ab3b4179
                .contentId(content.getId())
                .name(content.getName())
                .type(content.getType())
                .imgUrl(content.getImgUrl())
                .linkUrl(content.getLinkUrl())
                .genreList(content.getGenreList())
                .emotionList(content.getEmotionList())
                .build();
    }

//    private List<Content> recommendSpecialSituationForId1(ContentType type) {
//        // 상황 1에 대한 로직
//
////        return recommendedContents.stream()
////                .filter(content -> content.getGenreList().contains("DRAMA") || content.getGenreList().contains("ROMANCE"))
////                .map(this::mapContentToResponseDTO)
////                .collect(Collectors.toList());
//        return recommendedContents;
//    }
//
//    private List<ContentSpecialSituationResponse> recommendSpecialSituationForId2(ContentType type) {
//        List<Content> recommendedContents = contentRepository.findByType(type);
//        return recommendedContents.stream()
//                .filter(content -> content.getGenreList().contains("COMEDY") || content.getGenreList().contains("HIGHTEEN"))
//                .map(this::mapContentToResponseDTO)
//                .collect(Collectors.toList());
//    }
//
//    private List<ContentSpecialSituationResponse> recommendSpecialSituationForId3(ContentType type) {
//        List<Content> recommendedContents = contentRepository.findByType(type);
//        return recommendedContents.stream()
//                .filter(content -> content.getGenreList().contains("CRIME") || content.getGenreList().contains("ACTION") || content.getGenreList().contains("FANTASY"))
//                .map(this::mapContentToResponseDTO)
//                .collect(Collectors.toList());
//    }
//
//    private List<ContentSpecialSituationResponse> recommendSpecialSituationForId4(ContentType type) {
//        List<Content> recommendedContents = contentRepository.findByType(type);
//        return recommendedContents.stream()
//                .filter(content -> content.getGenreList().contains("ANIMATION"))
//                .map(this::mapContentToResponseDTO)
//                .collect(Collectors.toList());
//    }


//    private List<Content> recommendSpecialSituationForId1(ContentType type) {
//        // 상황 1에 대한 로직
//        List<Content> responseList = new ArrayList<>();
//
//        List<Content> recommendedContents = contentRepository.findByType(type);
//        for (Content content : recommendedContents) {
//            if (content.getGenreList().contains("DRAMA") || content.getGenreList().contains("ROMANCE")) {
//                responseList.add(content);
//            }
//        }
//        return responseList;
//    }
//
//    private List<Content> recommendSpecialSituationForId2(ContentType type) {
//        List<Content> responseList = new ArrayList<>();
//
//        List<Content> recommendedContents = contentRepository.findByType(type);
//        for (Content content : recommendedContents) {
//            if (content.getGenreList().contains("COMEDY") || content.getGenreList().contains("HIGHTEEN")) {
//                responseList.add(content);
//            }
//        }
//        return responseList;
//    }
//    private List<Content> recommendSpecialSituationForId3(ContentType type) {
//        List<Content> responseList = new ArrayList<>();
//
//        List<Content> recommendedContents = contentRepository.findByType(type);
//        for (Content content : recommendedContents) {
//            if (content.getGenreList().contains("CRIME") || content.getGenreList().contains("ACTION")|| content.getGenreList().contains("FANTASY")) {
//                responseList.add(content);
//            }
//        }
//        return responseList;
//    }
//
//    private List<Content> recommendSpecialSituationForId4(ContentType type) {
//        List<Content> responseList = new ArrayList<>();
//
//        List<Content> recommendedContents = contentRepository.findByType(type);
//        for (Content content : recommendedContents) {
//            if (content.getGenreList().contains("ANIMATION")) {
//                responseList.add(content);
//            }
//        }
//        return responseList;
//    }

//    private ContentResponseDTO convertToDTO(Content content) {
//        return ContentResponseDTO.builder()
//                .contentId(content.getId())
//                .name(content.getName())
//                .type(content.getType()) // Enum 타입을 문자열로 변환
//                .genreList(content.getGenreList())
//                .emotionList(content.getEmotionList())
//                .imgUrl(content.getImgUrl())
//                .linkUrl(content.getLinkUrl())
//                .build();
//    }
}

