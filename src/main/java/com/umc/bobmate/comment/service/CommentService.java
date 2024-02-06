package com.umc.bobmate.comment.service;

import com.umc.bobmate.comment.domain.Comment;
import com.umc.bobmate.comment.domain.repository.CommentRepository;
import com.umc.bobmate.comment.dto.CommentResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public List<CommentResponseDTO> makeTop4SituationFromComments() {
        List<Comment> comments = commentRepository.findMostFrequentCommentList();


        List<CommentResponseDTO> response = comments.stream()
                .map(comment -> CommentResponseDTO.builder()
                        .commentId(comment.getId())
                        .emotion(comment.getEmotion())
                        .genre(comment.getGenre())
                        .food(comment.getFood())
                        .emotion(comment.getEmotion())
                        .build())
                .collect(Collectors.toList());
        return response;
    }
}




