package com.umc.bobmate.comment.controller;
import com.umc.bobmate.comment.dto.CommentResponseDTO;
import com.umc.bobmate.comment.service.CommentService;
import com.umc.bobmate.global.apiPayload.ApiResponse;
import com.umc.bobmate.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static com.umc.bobmate.global.apiPayload.code.status.ErrorStatus._BAD_REQUEST;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
public class CommentController {
    private final CommentService commentService;

    // 가장 많은 음식+감정의 것을 리턴
    @PostMapping("/make/situation")
    public ApiResponse<List<CommentResponseDTO>> getFrequentCommentResponse() {
        System.out.println("checking for make comment start");
        try {
            return ApiResponse.onSuccess(commentService.makeTop4SituationFromComments());
        } catch (Exception e) {
            throw new GeneralException(_BAD_REQUEST);
        }
    }
}
