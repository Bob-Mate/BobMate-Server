package com.umc.bobmate.menu.domain;

import static lombok.AccessLevel.PROTECTED;

import com.umc.bobmate.common.BaseEntity;
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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
public class Menu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private MenuType type;

    private String imageUrl;
    private String linkUrl;

    @OneToMany(mappedBy = "menu")
    private List<Like> likes = new ArrayList<>();

}
