package com.umc.bobmate.content.service;

import com.umc.bobmate.comment.domain.Comment;
import com.umc.bobmate.comment.domain.repository.CommentRepository;
import com.umc.bobmate.content.domain.Content;
import com.umc.bobmate.content.domain.ContentType;
import com.umc.bobmate.content.domain.Genre;
import com.umc.bobmate.content.domain.repository.ContentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.umc.bobmate.content.dto.ContentResponse;
import com.umc.bobmate.content.dto.ContentSpecialResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class ContentService {
    private final ContentRepository contentRepository;
    private final CommentRepository commentRepository;

    public List<ContentResponse> getTop3ContentsByLikes(ContentType contentType) {
        Pageable pageable = PageRequest.of(0, 3);
        List<Content> top3Contents = contentRepository.findTop3ByOrderByLikesCountDesc(contentType, pageable);
        return top3Contents.stream()
                .map(this::mapContentToResponse)
                .collect(Collectors.toList());
    }

    private ContentResponse mapContentToResponse(Content content) {
        return ContentResponse.builder()
                .contentId(content.getId())
                .name(content.getName())
                .imgUrl(content.getImgUrl())
                .linkUrl(content.getLinkUrl())
                .build();
    }

    @Transactional
    public List<Content> recommendContents(String emotion, String withWhom, ContentType contentType) {
        List<Content> recommendedContents = contentRepository.findContentWithoutBadEvaluation(contentType);
        List<Content> recommend = new ArrayList<>();

        List<Content> recommendFirst = contentRepository.recommendAgain(contentType);

        // 좋아요 누른 컨텐츠 중에서 감정이 겹치는 것이면 더하기
        if (recommendFirst != null) {
            for (Content ca : recommendFirst) {
                if (recommend.size() < 1 ) {
                        if(ca.getEmotionList().contains(emotion)) recommend.add(ca);
                }
            }
        }

        for (Content content : recommendedContents) {

            // 감정에 해당하는 경우에만 추가
            switch (emotion) {
                case "GLAD":
                    if (content.getGenreList().contains("COMIC") || content.getGenreList().contains("ANIMATION") || content.getGenreList().contains("ROMANCE")) {
                        if (recommend.size() < 3) {
                            if (!recommend.contains(content)) recommend.add(content);
                        }
                    }
                    break;

                case "EXCITED":
                    if (content.getGenreList().contains("ACTION") || content.getGenreList().contains("COMIC") || content.getGenreList().contains("ROMANCE")) {
                        if (recommend.size() < 3) {
                            if (!recommend.contains(content)) recommend.add(content);
                        }

                    }
                    break;

                case "GLOOMY":
                    if (content.getGenreList().contains("DRAMA") || content.getGenreList().contains("ACTION") || content.getGenreList().contains("ANIMATION")) {
                        if (recommend.size() < 3) {
                            if (!recommend.contains(content)) recommend.add(content);
                        }
                    }
                    break;

                case "ANGRY":
                    if (content.getGenreList().contains("ACTION") || content.getGenreList().contains("THRILLER") || content.getGenreList().contains("CRIME")) {
                        if (recommend.size() < 3) {
                            if (!recommend.contains(content)) recommend.add(content);
                        }
                    }
                    break;

                case "SAD":
                    if (content.getGenreList().contains("DRAMA") || content.getGenreList().contains("ROMANCE") || content.getGenreList().contains("FAMILY")) {
                        if (recommend.size() < 3) {
                            if (!recommend.contains(content)) recommend.add(content);
                        }
                    }
                    break;
            }
        }
        return recommend;
    }

    // 여기서 처리하기 - commentId로 장르가져와? 그 코멘트 아이디에 따른 콘텐츠 추천해주기
    @Transactional
    public List<ContentSpecialResponse> findContentWithFrequentGenre(Long commentId, ContentType type) {
        List<Content> listContent = new ArrayList<>();

        Comment comment = commentRepository.findCommentById(commentId);
        Genre genre = comment.getGenre();
        String sGenre = genre.toString();

        List<Content> recommendFirst = contentRepository.recommendAgain(type);

        if (recommendFirst != null) {
            for (Content ca : recommendFirst) {
                if (recommendFirst.size() < 2) listContent.add(ca);
            }
        }

        List<Content> recommendedContents = contentRepository.findByType(type);

        for (Content c : recommendedContents) {
            if (c.getGenreList().contains(sGenre)) {
                if (!listContent.contains(c) && listContent.size()<3) listContent.add(c);
            }
        }

        for (Content c : listContent){
            System.out.println("content Check : "+c.getName());
        }

        return listContent.stream()
                .map(content-> mapContentSpecialToResponse(content,comment))
                .collect(Collectors.toList());

    }

    private ContentSpecialResponse mapContentSpecialToResponse(Content content, Comment comment) {
        return ContentSpecialResponse.builder()
                .contentId(content.getId())
                .name(content.getName())
                .imgUrl(content.getImgUrl())
                .linkUrl(content.getLinkUrl())
                .commentId(comment.getId())
                .food(comment.getFood())
                .emotion(comment.getEmotion())
                .genre(comment.getGenre())
                .build();
    }

}
