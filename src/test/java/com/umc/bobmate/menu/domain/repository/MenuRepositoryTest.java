package com.umc.bobmate.menu.domain.repository;

import static com.umc.bobmate.menu.domain.MenuType.*;

import com.umc.bobmate.content.domain.Content;
import com.umc.bobmate.menu.domain.Menu;
import com.umc.bobmate.menu.domain.MenuType;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class MenuRepositoryTest {
    @Autowired
    MenuRepository menuRepository;
//
//    @Test
//    @DisplayName("테스트 데이터 생성용 코드")
//    void addTestMenuData() {
//        String name = "단호박 두부 샐러디";
//        MenuType contentType = LUNCH;
//        String imgUrl = "https://salady.com/superboard/data/product/thumb/990495120_d8Ys9lUM_fc34d1ddea642a499b40b73a7e6c6bb02b7d0ff8.png";
//
////        String name = "리코타치즈 샌드위치";
////        MenuType contentType = BREAKFAST;
////        String imgUrl = "https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20230519_194%2F1684479298017Gj6dj_JPEG%2F20230331_131400-01.jpeg";
//
//        final Menu menu = Menu.builder()
//                .name(name)
//                .menuType(contentType)
//                .imgUrl(imgUrl)
//                .build();
//
//        menuRepository.save(menu);
//    }
}