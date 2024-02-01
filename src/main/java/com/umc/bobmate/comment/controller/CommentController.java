package com.umc.bobmate.comment.controller;

import com.umc.bobmate.comment.domain.Comment;
import com.umc.bobmate.comment.domain.repository.CommentRepository;
import com.umc.bobmate.comment.dto.CommentRequestDTO;
import com.umc.bobmate.comment.dto.CommentResponseDTO;
import com.umc.bobmate.comment.service.CommentService;
import com.umc.bobmate.content.domain.Content;
import com.umc.bobmate.content.domain.ContentType;
import com.umc.bobmate.content.domain.Emotion;
import com.umc.bobmate.content.domain.Genre;
import com.umc.bobmate.content.domain.repository.ContentRepository;
import com.umc.bobmate.content.dto.ContentResponseDTO;
import com.umc.bobmate.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/Comment")
public class CommentController {
    private final CommentService commentService;
    private final CommentRepository commentRepository;
    private final ContentRepository contentRepository;

    // 가장 많은 음식+감정의 것을 리턴 -> 특별한 상황 반영을 위한 comment 내용 전달
    @PostMapping("/situation")
    public ApiResponse<List<CommentResponseDTO>> getCommentsByFoodAndEmotion(
            @RequestParam String food,
            @RequestParam Emotion emotion,
            @RequestParam Genre genre) {

        // 필터링 된 코멘트 1개 가져오기 : 서비스 MAU는 구현을 못해서 findCommentsWithSameEmotionAndFood에서 구현을 해야..
        List<Comment> filteredComments = commentService.findCommentsWithSameEmotionAndFood(emotion, food);

        // CommentResponseDTO로 변환
        List<CommentResponseDTO> comment = filteredComments.stream()
                .map(comment1 -> CommentResponseDTO.builder()
                        .emotion(comment1.getEmotion())
                        .food(comment1.getFood())
                        .genre(comment1.getGenre())
                        .memberId(comment1.getId())
                        .build())
                .toList();

        return ApiResponse.onSuccess(comment);

    }




}
