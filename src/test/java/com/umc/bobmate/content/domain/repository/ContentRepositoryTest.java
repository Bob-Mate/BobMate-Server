package com.umc.bobmate.content.domain.repository;

import com.umc.bobmate.content.domain.Content;
import com.umc.bobmate.content.domain.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.umc.bobmate.content.domain.ContentType.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ContentRepositoryTest {
    @Autowired
    ContentRepository contentRepository;

    @Test
    @DisplayName("테스트 데이터 생성용 코드")
    void addTestContentData() {
        String name = "선산";
        ContentType contentType = VIDEO;


        Content.builder()

    }
}