package com.umc.bobmate.content.domain.repository;

import com.umc.bobmate.content.domain.Content;
import com.umc.bobmate.content.domain.ContentType;
import java.util.List;

import com.umc.bobmate.content.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContentRepository extends JpaRepository<Content, Long> {

    @Query("SELECT c FROM Content c " +
            "LEFT JOIN c.likes l " +
            "WHERE c.type = :contentType " +
            "GROUP BY c.id " +
            "ORDER BY COUNT(l) DESC")
    List<Content> findTop3ContentsByLikesAndType(@Param("contentType") ContentType contentType);


    // 컨텐츠에서 type 기준으로 컨텐츠 가져오기
    @Query("SELECT c FROM Content c WHERE c.type = :type")
    List<Content> findByType(@Param("type") ContentType type);

    @Query("SELECT c FROM Content c " +
            "INNER JOIN Evaluation e on c.id = e.id where e.isGood=true")
    List<Content> recommendAgain();

    @Query("SELECT c FROM Content c " +
            "INNER JOIN Evaluation e ON c.id = e.id WHERE e.isGood <> false")
    List<Content> findContentWithBadEvaluation();

    // Long findById();

    //@Query("SELECT c FROM Content c WHERE")
    // List<Content> findContentsByTypeAndGenreList();

}






