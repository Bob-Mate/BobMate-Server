package com.umc.bobmate.member.domain;

import static jakarta.persistence.EnumType.*;
import static lombok.AccessLevel.PROTECTED;

import com.umc.bobmate.common.BaseEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@DynamicInsert
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

    @Enumerated(STRING)
    private OAuthProvider oAuthProvider;

    private String socialId;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "Preference",
            joinColumns = @JoinColumn(name = "memberId")
    )
    @Column(name = "memo")
    private List<String> preferenceList = new ArrayList<>();

    @Builder
    public Member(String name, String socialId, String imageUrl, OAuthProvider oAuthProvider) {
        this.name = name;
        this.socialId = socialId;
        this.imageUrl = imageUrl;
        this.oAuthProvider = oAuthProvider;
    }
}
