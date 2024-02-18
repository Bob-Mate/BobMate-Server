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
import com.umc.bobmate.evaluation.domain.Evaluation;
import com.umc.bobmate.evaluation.domain.repository.EvaluationRepository;
import com.umc.bobmate.login.jwt.util.AuthTokensGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ContentService {
    private final ContentRepository contentRepository;
    private final CommentRepository commentRepository;
    private final EvaluationRepository evaluationRepository;
    private final AuthTokensGenerator authTokensGenerator;


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

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Content> recommendContents(String emotion, String withWhom, ContentType contentType) {
        Long memberId = authTokensGenerator.getLoginMemberId();
        List<Content> recommendedContents = contentRepository.findByType(contentType);
        List<Content> excludeContents = contentRepository.findBadEvaluation(memberId, contentType);
        List<Evaluation> goodContents = evaluationRepository.findTopContentSumScore();

        List<Content> recommend = new ArrayList<>();
        List<String> dgenre = genreByEmotion(emotion);

        for (Evaluation e : goodContents){
            for (String s : dgenre){
                if (e.getContent().getGenreList().contains(s)
                        && e.getContent().getType()==contentType && recommend.size()<1
                        && !excludeContents.contains(e.getContent()) && !recommend.contains(e.getContent())) {
                    recommend.add(e.getContent());
                }
            }

        }

        for (Content content : recommendedContents) {

            // 감정에 따른 장르 해당 컨텐츠 추천
            switch (emotion) {
                case "GLAD":
                    if (content.getGenreList().contains("COMIC") || content.getGenreList().contains("ANIMATION") || content.getGenreList().contains("ROMANCE")) {
                        if (recommend.size() < 3 && !excludeContents.contains(content)) {
                            if (!recommend.contains(content)) recommend.add(content);
                        }
                    }
                    break;

                case "EXCITED":
                    if (content.getGenreList().contains("ACTION") || content.getGenreList().contains("COMIC") || content.getGenreList().contains("ROMANCE")) {
                        if (recommend.size() < 3 && !excludeContents.contains(content)) {
                            if (!recommend.contains(content)) recommend.add(content);
                        }

                    }
                    break;

                case "GLOOMY":
                    if (content.getGenreList().contains("DRAMA") || content.getGenreList().contains("ACTION")
                            || content.getGenreList().contains("ANIMATION")) {
                        if (recommend.size() < 3 && !excludeContents.contains(content)) {
                            if (!recommend.contains(content)) recommend.add(content);
                        }
                    }
                    break;

                case "ANGRY":
                    if (content.getGenreList().contains("ACTION") || content.getGenreList().contains("THRILLER") || content.getGenreList().contains("CRIME")) {
                        if (recommend.size() < 3 && !excludeContents.contains(content)) {
                            if (!recommend.contains(content)) recommend.add(content);
                        }
                    }
                    break;

                case "SAD":
                    if (content.getGenreList().contains("DRAMA") || content.getGenreList().contains("ROMANCE") || content.getGenreList().contains("FAMILY")) {
                        if (recommend.size() < 3 && !excludeContents.contains(content)) {
                            if (!recommend.contains(content)) recommend.add(content);
                        }
                    }
                    break;
            }
        }
        return recommend;
    }

    // 여기서 처리하기 - commentId로 장르가져와? 그 코멘트 아이디에 따른 콘텐츠 추천해주기
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<ContentSpecialResponse> findContentWithFrequentGenre(Long commentId, ContentType type) {
        Long memberId = authTokensGenerator.getLoginMemberId();
        List<Content> recommendedContents = contentRepository.findByType(type);
        List<Content> excludeContents = contentRepository.findBadEvaluation(memberId, type);
        List<Evaluation> goodContents = evaluationRepository.findTopContentSumScore();

        List<Content> listContent = new ArrayList<>();

        Comment comment = commentRepository.findCommentById(commentId);
        Genre genre = comment.getGenre();
        String sGenre = genre.toString();

        for (Evaluation e : goodContents){
            System.out.println(e.getContent().getName()+" score is "+e.getScore());
            if (e.getContent().getGenreList().contains(sGenre)
                    && listContent.size()<1 && e.getContent().getType()==type) listContent.add(e.getContent());
        }

        // 새로고침하면 비추천한것도 반영
        for (Content c : recommendedContents) {
            if (c.getGenreList().contains(sGenre)) {
                if (!listContent.contains(c) && listContent.size()<3 && !excludeContents.contains(c)) {
                    listContent.add(c);
                }
            }
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

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<ContentResponse> refresh(String emotion, String withWhom, ContentType contentType){
        List<Content> recommendContents = recommendContents(emotion, withWhom, contentType);
        List<Long> exclude = new ArrayList<>();
        for (Content c : recommendContents){
            exclude.add(c.getId());
        }
        List<Content> excludedContents = contentRepository.findOtherContents(exclude, contentType);

        List<Content> newContents = new ArrayList<>();
        for (Content c : excludedContents) {
            if(newContents.size()<3) newContents.add(c);
        }
        return newContents.stream()
                .map(this::mapContentToResponse)
                .collect(Collectors.toList());
    }

    private List<String> genreByEmotion(String emotion){
        List<String> genre = new ArrayList<>();
        switch (emotion) {
            case "GLAD":
                 genre.add("COMIC"); genre.add("ANIMATION"); genre.add("ROMANCE");
                 break;

            case "EXCITED":
                genre.add("ACTION"); genre.add("COMIC"); genre.add("ROMANCE");
                break;

            case "GLOOMY":
                genre.add("DRAMA"); genre.add("ACTION"); genre.add("ANIMATION");
                break;

            case "ANGRY":
                genre.add("ACTION"); genre.add("THRILLER"); genre.add("CRIME");
                break;

            case "SAD":
                genre.add("DRAMA"); genre.add("ROMANCE"); genre.add("FAMILY");
                break;
        }
        return genre;
    }

}
