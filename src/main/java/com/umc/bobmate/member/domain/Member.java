package com.umc.bobmate.member.domain;

import static lombok.AccessLevel.PROTECTED;

import com.umc.bobmate.comment.domain.Comment;
import com.umc.bobmate.common.BaseEntity;
import com.umc.bobmate.evaluation.domain.Evaluation;
import com.umc.bobmate.like.domain.Like;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String imageUrl;

    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Evaluation> evaluations = new ArrayList<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "Preference",
            joinColumns = @JoinColumn(name = "memberId")
    )
    @Column(name = "memo")
    private List<String> preferenceList = new ArrayList<>();

}
