package com.umc.bobmate.menu.service;

import static com.umc.bobmate.menu.domain.MenuType.*;

import com.umc.bobmate.menu.domain.Menu;
import com.umc.bobmate.menu.domain.MenuType;
import com.umc.bobmate.menu.domain.repository.MenuRepository;
import com.umc.bobmate.menu.dto.MenuResponse;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public List<MenuResponse> getRandomMenus() {
        // 각 MenuType에 대해 랜덤하게 1개씩 선택하고 MenuResponse로 변환
        return Stream.of(BREAKFAST, LUNCH, DINNER, SNACK)
                .map(this::getRandomMenuByType)
                .map(this::convertToMenuResponse)
                .collect(Collectors.toList());
    }

    private Menu getRandomMenuByType(MenuType type) {
        List<Menu> menuList = menuRepository.findByType(type);

        if (!menuList.isEmpty()) {
            Random random = new Random();
            int index = random.nextInt(menuList.size());
            return menuList.get(index);
        } else {
            return null;
        }
    }

    private MenuResponse convertToMenuResponse(Menu menu) {
        return MenuResponse.builder()
                .menuId(menu.getId())
                .menuType(menu.getType())
                .name(menu.getName())
                .imgUrl(menu.getImgUrl())
                .build();
    }

}
