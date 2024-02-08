package com.umc.bobmate.content.dto;

import com.umc.bobmate.content.domain.Emotion;
import com.umc.bobmate.content.domain.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ContentSpecialResponse {
    private Long commentId;
    private Emotion emotion;
    private String food;
    private Genre genre;
    private Long contentId;
    private String name;
    private String imgUrl;
    private String linkUrl;


    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public void setEmotion(Emotion emotion) {
        this.emotion = emotion;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }


}
