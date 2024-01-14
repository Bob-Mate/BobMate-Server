package com.umc.bobmate.content.domain;

import static lombok.AccessLevel.PROTECTED;

import com.umc.bobmate.common.BaseEntity;
import com.umc.bobmate.evaluation.domain.Evaluation;
import com.umc.bobmate.like.domain.Like;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Content extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ContentType type;

    @Enumerated(EnumType.STRING)
    private Genre genre;
    //private List<Genre> genreList;

    @Enumerated(EnumType.STRING)
    private Emotion emotion;

    private String imageUrl;
    private String linkUrl;

    @OneToMany(mappedBy = "content")
    private List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "content")
    private List<Evaluation> evaluations = new ArrayList<>();

}
