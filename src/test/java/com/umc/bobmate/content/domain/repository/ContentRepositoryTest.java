package com.umc.bobmate.content.domain.repository;

import com.umc.bobmate.content.domain.Content;
import com.umc.bobmate.content.domain.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static com.umc.bobmate.content.domain.ContentType.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ContentRepositoryTest {
    @Autowired
    ContentRepository contentRepository;
<<<<<<< HEAD

=======
//
>>>>>>> ff82077c6f0f16f906e47ae39d6233050ac19f2a
//    @Test
//    @DisplayName("테스트 데이터 생성용 코드")
//    void addTestContentData() {
//        String name = "웰컴투 심달리";
//        ContentType contentType = VIDEO;
//        List<String> genreList = new ArrayList<>();
//        genreList.add("DRAMA");
//        genreList.add("ROMANCE");
//
//        List<String> emotionList = new ArrayList<>();
//        emotionList.add("SAD");
//
//        String imgUrl = "https://file.kinolights.com/xl/content_poster/202311/13/4e38c9b6-d869-4fd7-9f53-3e63ea5b13be.webp";
//        String linkUrl = "https://youtu.be/12tlkwVgloA?si=o6MrNpXHYX22cqwP";
//
//        final Content content = Content.builder()
//                .name(name)
//                .contentType(contentType)
//                .genreList(genreList)
//                .emotionList(emotionList)
//                .imgUrl(imgUrl)
//                .linkUrl(linkUrl)
//                .build();
//
//        contentRepository.save(content);
//    }
}