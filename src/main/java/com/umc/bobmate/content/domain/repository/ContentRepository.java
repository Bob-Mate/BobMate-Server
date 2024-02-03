package com.umc.bobmate.content.domain.repository;

import com.umc.bobmate.content.domain.Content;
import com.umc.bobmate.content.domain.ContentType;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContentRepository extends JpaRepository<Content, Long> {

    @Query("SELECT c FROM Content c WHERE c.type = :contentType ORDER BY c.likesCount DESC")
    List<Content> findTop3ByOrderByLikesCountDesc(@Param("contentType") ContentType contentType, Pageable pageable);

    // 컨텐츠에서 type 기준으로 컨텐츠 가져오기
    @Query("SELECT c FROM Content c WHERE c.type = :type")
    List<Content> findByType(@Param("type") ContentType type);

    // 추천누른 컨텐츠 다시 추천해주는 쿼리문 작성
    @Query("SELECT c FROM Content c " +
            "INNER JOIN Evaluation e on c.id = e.id where e.isGood=true")
    List<Content> recommendAgain();

    @Query("SELECT c FROM Content c " +
            "INNER JOIN Evaluation e ON c.id = e.id WHERE e.isGood <> false")
    List<Content> findContentWithBadEvaluation();


    //@Query("SELECT c FROM Content c WHERE")
    // List<Content> findContentsByTypeAndGenreList();


}






