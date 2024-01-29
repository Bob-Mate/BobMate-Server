package com.umc.bobmate.member.dto.request;

import com.umc.bobmate.content.domain.Emotion;
import com.umc.bobmate.content.domain.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentUploadRequest {
    private String food;
    private Emotion emotion;
    private Genre genre;
}
