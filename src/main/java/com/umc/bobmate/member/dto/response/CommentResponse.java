package com.umc.bobmate.member.dto.response;

import com.umc.bobmate.comment.domain.Comment;
import com.umc.bobmate.content.domain.Emotion;
import com.umc.bobmate.content.domain.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {
    private Emotion emotion;
    private String food;
    private Genre genre;

    public CommentResponse(Comment comment) {
        if (comment != null) {
            this.emotion = comment.getEmotion();
            this.food = comment.getFood();
            this.genre = comment.getGenre();
        }
    }
}
