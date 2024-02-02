package com.umc.bobmate.member.domain;

import com.umc.bobmate.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@DynamicInsert
@Builder
@AllArgsConstructor
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
    public Member(
            final String name,
            final String socialId,
            final String imageUrl,
            final OAuthProvider oAuthProvider
    ) {
        this.name = name;
        this.socialId = socialId;
        this.imageUrl = imageUrl;
        this.oAuthProvider = oAuthProvider;
    }

    public void modifyPreference(final List<String> preferenceList) {
        this.preferenceList = preferenceList;
    }

    public void modifyName(final String name) {
        this.name = name;
    }

    public void deleteProfileImage() {
        this.imageUrl = null;
    }

    public void modifyProfileImage(String imgUrl) {
        this.imageUrl = imgUrl;
    }

}
