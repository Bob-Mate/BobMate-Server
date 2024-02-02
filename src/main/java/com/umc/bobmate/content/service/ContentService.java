package com.umc.bobmate.content.service;

import com.umc.bobmate.comment.domain.Comment;
import com.umc.bobmate.comment.domain.repository.CommentRepository;
import com.umc.bobmate.comment.dto.CommentResponseDTO;
import com.umc.bobmate.content.domain.Content;
import com.umc.bobmate.content.domain.ContentType;
import com.umc.bobmate.content.domain.Emotion;
import com.umc.bobmate.content.domain.Genre;
import com.umc.bobmate.content.domain.repository.ContentRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.umc.bobmate.content.dto.ContentResponseDTO;
import com.umc.bobmate.content.dto.ContentSpecialSituationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class ContentService {
    private final ContentRepository contentRepository;
    private final CommentRepository commentRepository;

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
                            if (recommend.size()<3) recommend.add(content);
                        }
                        break;

                    case "EXCITED":
                        if (content.getGenreList().contains("ACTION") || content.getGenreList().contains("ROMANTIC_COMEDY") || content.getGenreList().contains("ROMANCE")) {
                            if (recommend.size()<3) recommend.add(content);
                        }
                        break;

                    case "GLOOMY":
                        if (content.getGenreList().contains("DRAMA") || content.getGenreList().contains("ACTION") || content.getGenreList().contains("ANIMATION")) {
                            if (recommend.size()<3) recommend.add(content);
                        }
                        break;

                    case "ANGRY":
                        if (content.getGenreList().contains("ACTION") || content.getGenreList().contains("THRILLER") || content.getGenreList().contains("CRIME")) {
                            if (recommend.size()<3) recommend.add(content);
                        }
                        break;

                    case "SAD":
                        if (content.getGenreList().contains("DRAMA") || content.getGenreList().contains("ROMANCE") || content.getGenreList().contains("FAMILY")) {
                            if (recommend.size()<3) recommend.add(content);
                        }
                        break;
                }
            }
        }
        return recommend;
    }

    @Transactional
    public List<Content> findSpecialContent(CommentResponseDTO dto, ContentType contentType) {
        List<Content> recommendedContents = contentRepository.findByType(contentType);

        List<Content> recommend = new ArrayList<>();
        Genre gen = dto.getGenre();
        String genre = gen.toString();
        for (Content content : recommendedContents) {
            List<String> genreList = content.getGenreList();
            if (genreList.contains(genre)) { //해당 컨텐츠의 장르 리스트에 뽑은 장르가 포함되면 그 컨텐츠 추천
                if(recommend.size()<3) recommend.add(content);
            }
        }
        return recommend;
    }

    public List<Comment> findCommentsWithSameEmotionAndFood(Emotion emotion, String food) {
        return commentRepository.findCommentsByEmotionAndFood(emotion, food);
    }

    public Genre getRecommendedGenre(Emotion emotion, String food) {
        // 1. 주어진 감정과 음식으로 필터링된 Comment 목록을 가져옵니다.
        List<Comment> filteredComments = findCommentsWithSameEmotionAndFood(emotion, food);

        // 2. 필터링된 Comment 목록에서 각 장르의 등장 횟수를 계산합니다.
        Map<Genre, Long> genreCountMap = filteredComments.stream()
                .collect(Collectors.groupingBy(Comment::getGenre, Collectors.counting()));

        // 3. 등장 횟수가 가장 높은 장르를 선택합니다.
        return genreCountMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }


//    @Transactional
//    public Content recommendContent(String emotion, Genre genre) {
//        Content recommendedContent = contentRepository.findByType(contentType);
//
//        List<Content> contents = contentRepository.findByType(type);
//        for (Content c : contents){
//
//        }
//    }
//    return recommend;



//    @Transactional
//    public List<Content> recommendSpecialSituation(Genre genre, ContentType type) {
//        // 장르와 타입에 따른 컨텐츠 추천
//        genre = getRecommendedGenre();
//        List<Content> recommendedContents = contentRepository.findByType(type);
//        List<Content> recommend = new ArrayList<>();
//
//
//        return recommend;
//    }

    public ContentSpecialSituationResponse mapContentToResponseDTO(Content content) {
        return ContentSpecialSituationResponse.builder()
                .contentId(content.getId())
                .name(content.getName())
                .type(content.getType())
                .imgUrl(content.getImgUrl())
                .linkUrl(content.getLinkUrl())
                .genreList(content.getGenreList())
                .emotionList(content.getEmotionList())
                .build();
    }

    public Optional<Content> getContentById(Long contentId) {
        return contentRepository.findById(contentId);
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


//for(Content c : recommendedContents){
//        switch (id) {
//        case 1:
//        if (c.getGenreList().contains("DRAMA") || c.getGenreList().contains("ROMANCE"))
//        recommend.add(c);
//        break;
//
//        case 2:
//        if (c.getGenreList().contains("COMEDY") || c.getGenreList().contains("HIGHTEEN"))
//        recommend.add(c);
//        break;
//
//        case 3:
//        if (c.getGenreList().contains("CRIME") || c.getGenreList().contains("ACTION") || c.getGenreList().contains("FANTASY"))
//        recommend.add(c);
//        break;
//
//        case 4:
//
//        if (c.getGenreList().contains("ANIMATION"))
//        recommend.add(c);
//        break;
//
//default:
//        throw new IllegalArgumentException("Invalid id: " + id);
//        }
//
//        }

