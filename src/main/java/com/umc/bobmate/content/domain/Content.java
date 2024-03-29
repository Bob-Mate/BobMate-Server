package com.umc.bobmate.content.domain;

import static lombok.AccessLevel.PROTECTED;

import com.umc.bobmate.common.BaseEntity;
import com.umc.bobmate.like.domain.Likes;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@DynamicInsert

@NoArgsConstructor(access = PROTECTED)
public class Content extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ContentType type;


    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "Genre",
            joinColumns = @JoinColumn(name = "contentId")
    )
    @Column(name = "genre")
    private List<String> genreList = new ArrayList<>();


    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "Emotion",
            joinColumns = @JoinColumn(name = "contentId")
    )
    @Column(name = "emotion")
    private List<String> emotionList = new ArrayList<>();

    private String imgUrl;

    private String linkUrl;


    // 좋아요 개수
    @Column(name = "likes_count")
    private Long likesCount;


    public void setLikesCount(long likesCount) {
        this.likesCount = likesCount;
    }


    @OneToMany(mappedBy = "content")
    private List<Likes> likes = new ArrayList<>();

    @Builder
    public Content(String name, ContentType contentType, List<String> genreList, List<String> emotionList, String imgUrl, String linkUrl) {
        this.name = name;
        this.type = contentType;
        this.genreList = genreList;
        this.emotionList = emotionList;
        this.imgUrl = imgUrl;
        this.linkUrl = linkUrl;
    }
}


//    @Builder
//    public Content(String name, ContentType contentType, List<String> genreList, List<String> emotionList, String imgUrl, String linkUrl) {
//        this.name = name;
//        this.type = contentType;
//        this.genreList = genreList;
//        this.emotionList = emotionList;
//        this.imgUrl = imgUrl;
//        this.linkUrl = linkUrl;
//    }






