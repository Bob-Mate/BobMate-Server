package com.umc.bobmate.comment.domain;

import com.umc.bobmate.common.BaseEntity;
import com.umc.bobmate.content.domain.Emotion;
import com.umc.bobmate.content.domain.Genre;
import com.umc.bobmate.member.domain.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("NULL")
    private Emotion emotion;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("NULL")
    private Genre genre;

    private String food;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

}
