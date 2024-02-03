package com.umc.bobmate.comment.controller;

import com.umc.bobmate.comment.domain.Comment;
import com.umc.bobmate.comment.domain.repository.CommentRepository;
import com.umc.bobmate.comment.dto.CommentRequestDTO;
import com.umc.bobmate.comment.dto.CommentResponseDTO;
import com.umc.bobmate.comment.service.CommentService;
import com.umc.bobmate.content.domain.repository.ContentRepository;
import com.umc.bobmate.global.apiPayload.ApiResponse;
import com.umc.bobmate.global.apiPayload.exception.GeneralException;
import com.umc.bobmate.login.jwt.util.AuthTokensGenerator;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.umc.bobmate.global.apiPayload.code.status.ErrorStatus._BAD_REQUEST;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@RequestMapping("/api/v1/Comment")
public class CommentController {
    private final CommentService commentService;
    private final CommentRepository commentRepository;
    private final ContentRepository contentRepository;
    private final AuthTokensGenerator authTokensGenerator;

    // 가장 많은 음식+감정의 것을 리턴 -> 특별한 상황 반영을 위한 comment 내용 전달
    // 저장 누르면 코멘트 저장
    @PostMapping("/create")
    public ApiResponse<List<CommentResponseDTO>> getCommentsByFoodAndEmotion(
//            @RequestParam String food,
//            @RequestParam Emotion emotion,
//            @RequestParam Genre genre
        @RequestBody CommentRequestDTO dto

    )
         {


        // 필터링 된 코멘트 가져오기 : 서비스 MAU는 구현을 못해서 findCommentsWithSameEmotionAndFood에서 구현을 해야..
        try{
            Long memberId = authTokensGenerator.getLoginMemberId();
            List<Comment> comment2 = commentService.makeCommentList(dto);
            //List<Comment> filteredComments = commentService.findCommentsWithSameEmotionAndFood(emotion, food);
            List<CommentResponseDTO> comment = comment2.stream()
                    .map(comment1 -> CommentResponseDTO.builder()
                            .emotion(comment1.getEmotion())
                            .food(comment1.getFood())
                            .genre(comment1.getGenre())
                            .memberId(comment1.getMember().getId())
                            .build())
                    .toList();

            return ApiResponse.onSuccess(comment);
        } catch (Exception e){
            throw new GeneralException(_BAD_REQUEST);
        }


    }

}
