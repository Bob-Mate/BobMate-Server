package com.umc.bobmate.like.domain;

import static lombok.AccessLevel.PROTECTED;

import com.umc.bobmate.common.BaseEntity;
import com.umc.bobmate.content.domain.Content;
import com.umc.bobmate.member.domain.Member;
import com.umc.bobmate.menu.domain.Menu;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@DynamicInsert
@NoArgsConstructor
public class Likes extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private LikeType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    private Content content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;


    public void setMember(Member member) {
        this.member = member;
        member.getLikes().add(this); // Member 엔티티에 Likes 추가
    }

    public void setContent(Content content) {
        this.content = content;
        content.getLikes().add(this); // Content 엔티티에 Likes 추가
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
        menu.getLikes().add(this); // Menu 엔티티에 Likes 추가
    }

//
//
//    public void like(Content content) {
////        this.type = LikeType.LIKE;
//        this.content = content;
//        content.getLikes().add(this);
//    }
//
//    public void like(Menu menu) {
////        this.type = LikeType.LIKE;
//        this.menu = menu;
//        menu.getLikes().add(this);
//    }

//    public void unlike() {
//        if (content != null) {
//            content.getLikes().remove(this);
//            content = null;
//        }
//        if (menu != null) {
//            menu.getLikes().remove(this);
//            menu = null;
//        }
////        this.type = LikeType.UNLIKE;
//    }
//
//    // Content와 Menu에 좋아요 여부를 반영하는 메서드
//    public void reflectLikesState() {
//        if (content != null) {
//            content.setLiked(true);
//        }
//        if (menu != null) {
//            menu.setLiked(true);
//        }
//    }

}
