package com.umc.bobmate.content.service;

import com.umc.bobmate.content.domain.repository.ContentRepository;
import com.umc.bobmate.like.domain.repository.LikeRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ContentServiceTest {

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private ContentService contentService;

    @Autowired
    private LikeRepository likeRepository;
/*
    @Test
    @Transactional
    void testGetTop3Contents() {
        // given: DataGrip에 저장된 데이터 사용
        Content content1 = contentRepository.findById(1L).orElseThrow();
        Content content2 = contentRepository.findById(2L).orElseThrow();
        Content content3 = contentRepository.findById(3L).orElseThrow();

        connectLikes(content1, 15);
        connectLikes(content2, 14);
        connectLikes(content3, 13);

        // when
        List<ContentResponse> result = contentService.getTop3Contents(ContentType.VIDEO);

        // then
        assertEquals(3, result.size());
        assertEquals(content1.getId(), result.get(0).getContentId());
        assertEquals(content2.getId(), result.get(1).getContentId());
        assertEquals(content3.getId(), result.get(2).getContentId());
    }

    private void connectLikes(Content content, int numberOfLikes) {
        for (int i = 0; i < numberOfLikes; i++) {
            Likes like = new Likes();
            like.setContent(content);
            likeRepository.save(like);
        }
    }
*/
}