package com.umc.bobmate.menu.domain;

import static lombok.AccessLevel.PROTECTED;

import com.umc.bobmate.common.BaseEntity;
import com.umc.bobmate.content.domain.ContentType;
import com.umc.bobmate.like.domain.Likes;
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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@DynamicInsert
@NoArgsConstructor(access = PROTECTED)
public class Menu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private MenuType type;

    private String imgUrl;

    @OneToMany(mappedBy = "menu")
    private List<Likes> likes = new ArrayList<>();


    @Builder
    public Menu(String name, MenuType menuType, String imgUrl) {
        this.name = name;
        this.type = menuType;
        this.imgUrl = imgUrl;
    }
}
