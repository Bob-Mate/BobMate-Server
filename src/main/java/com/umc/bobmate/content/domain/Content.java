package com.umc.bobmate.content.domain;

import com.umc.bobmate.comment.domain.Comment;
import com.umc.bobmate.common.BaseEntity;
import com.umc.bobmate.evaluation.domain.Evaluation;
import com.umc.bobmate.like.domain.Like;
import com.umc.bobmate.preference.domain.Preference;
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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Content extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("NULL")
    private ContentType type;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("NULL")
    private Genre genre;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("NULL")
    private Emotion emotion;

    private String imageUrl;
    private String linkUrl;

    @OneToMany(mappedBy = "content")
    private List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "content")
    private List<Evaluation> evaluations = new ArrayList<>();

}
