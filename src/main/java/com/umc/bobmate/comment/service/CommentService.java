package com.umc.bobmate.comment.service;

import com.umc.bobmate.comment.domain.Comment;
import com.umc.bobmate.comment.domain.repository.CommentRepository;
import com.umc.bobmate.comment.dto.CommentRequestDTO;
import com.umc.bobmate.comment.dto.CommentResponseDTO;
import com.umc.bobmate.content.domain.Content;
import com.umc.bobmate.content.domain.ContentType;
import com.umc.bobmate.content.domain.Emotion;
import com.umc.bobmate.content.domain.Genre;
import com.umc.bobmate.content.service.ContentService;
import com.umc.bobmate.login.jwt.util.AuthTokensGenerator;
import com.umc.bobmate.member.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private final AuthTokensGenerator authTokensGenerator;
    private final CommentRepository commentRepository;
    private final ContentService contentService;

    public Long getCurrentMemberId() {
        return authTokensGenerator.getLoginMemberId();
    }

    public Member getCurrentMember() {
        return authTokensGenerator.getLoginMember();
    }

    @Autowired
    public CommentService(AuthTokensGenerator authTokensGenerator, CommentRepository commentRepository, ContentService contentService) {
        this.authTokensGenerator = authTokensGenerator;
        this.commentRepository = commentRepository;
        this.contentService = contentService;
    }

    // 감정 장르 음식 멤버 =>
    public List<Comment> makeCommentList(CommentRequestDTO dto) {
        Comment comment = new Comment(dto.getEmotion(), dto.getGenre(), dto.getFood(), dto.getMemberId());
        commentRepository.save(comment);

        List<Comment> commentList = new ArrayList<>();
        commentList.add(comment);

        return commentList;
    }
    // comment는 가장 많이 쓰인 감정+음식이 올라오고 이게 responseComment로 가서 상황 만들기
    // comment에서 음식+감정+장르를 추출해서 해당 장르 추천




//        List<Comment> comments = commentRepository.findByMemberId(getCurrentMemberId());
//
//        Comment aggregatedComment = aggregateComments(comments);
//
//        return createCommentResponseDTO(aggregatedComment);

//    public List<Comment> filterCommentsByFoodAndEmotion(String food, Emotion emotion) {
//        return commentRepository.findByFoodAndEmotion(food, emotion);
//    }

    public List<Comment> findCommentsWithSameEmotionAndFood(Emotion emotion, String food) {
        return commentRepository.findCommentsByEmotionAndFood(emotion, food);
    }

    //
    private Comment aggregateComments(List<Comment> comments) {
        Map<Emotion, Long> emotionCount = comments.stream()
                .collect(Collectors.groupingBy(Comment::getEmotion, Collectors.counting()));

        Map<Genre, Long> genreCount = comments.stream()
                .collect(Collectors.groupingBy(Comment::getGenre, Collectors.counting()));

        String mostFrequentEmotion = emotionCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .map(Emotion::name)
                .orElse(null);

        String mostFrequentGenre = genreCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .map(Genre::name)
                .orElse(null);

        return Comment.builder()
                .emotion(Emotion.valueOf(mostFrequentEmotion))
                .genre(Genre.valueOf(mostFrequentGenre))
                .build();
    }

//    private CommentResponseDTO createCommentResponseDTO(Comment comment) {
//        // 이 부분에서 ContentService를 활용하여 Content 추천을 받아옵니다.
//        Content recommendedContent = contentService.recommendContent(comment.getEmotion(), comment.getGenre());
//
//        return CommentResponseDTO.builder()
//                .contentId(recommendedContent.getId())
//                .name(recommendedContent.getName())
//                .type(recommendedContent.getType())
//                .imgUrl(recommendedContent.getImgUrl())
//                .linkUrl(recommendedContent.getLinkUrl())
//                .genreList(List.of(comment.getGenre().name()))
//                .emotionList(List.of(comment.getEmotion().name()))
//                .build();
//    }

//    public static CommentResponseDTO toDTO(Comment comment) {
//        return new CommentResponseDTO(comment.getFood(), comment.getEmotion());
//    }
}
