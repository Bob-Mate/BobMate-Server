package com.umc.bobmate.comment.domain;

import static lombok.AccessLevel.PROTECTED;

import com.umc.bobmate.common.BaseEntity;
import com.umc.bobmate.content.domain.Emotion;
import com.umc.bobmate.content.domain.Genre;
import com.umc.bobmate.member.domain.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@DynamicInsert
@NoArgsConstructor(access = PROTECTED)
@SQLRestriction("status = 'ACTIVE'")
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Emotion emotion;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private String food;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Comment(Emotion emotion, Genre genre, String food, Member member) {
        this.emotion = emotion;
        this.genre = genre;
        this.food = food;
        this.member = member;
    }

    public void show(){
        System.out.println("heeju test " + genre);
        System.out.println("heeju test " + emotion);
        System.out.println("heeju test " + food);
        System.out.println("heeju test " + id);

    }
}