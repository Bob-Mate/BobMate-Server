package com.umc.bobmate.member.domain;

import com.umc.bobmate.comment.domain.Comment;
import com.umc.bobmate.common.BaseEntity;
import com.umc.bobmate.evaluation.domain.Evaluation;
import com.umc.bobmate.like.domain.Like;
import com.umc.bobmate.preference.domain.Preference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private List<Preference> preferences = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Evaluation> evaluations = new ArrayList<>();

}
