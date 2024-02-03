package com.umc.bobmate.menu.dto;

import com.umc.bobmate.menu.domain.MenuType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MenuResponse {
    private Long menuId;
    private MenuType menuType;
    private String name;
    private String imgUrl;
}