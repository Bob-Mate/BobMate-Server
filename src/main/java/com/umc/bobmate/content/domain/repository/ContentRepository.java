package com.umc.bobmate.content.domain.repository;

import com.umc.bobmate.content.domain.Content;
import com.umc.bobmate.content.domain.ContentType;
import java.util.List;

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


    @Query("SELECT c FROM Content c WHERE c.type = :type")
    List<Content> findByType(@Param("type") ContentType type);

}
    //    @Query("SELECT c FROM Content c " +
    //            "LEFT JOIN FETCH c.likes " +
    //            "GROUP BY c.id " +
    //            "ORDER BY COUNT(c.likes) DESC")
    //    List<Content> findTop3ContentsByLikes();


/*
    List<Content> findRecommendComponents
            (String emotion, String contentType, String genre);

    // emotion과 type을 통한
    @Query("SELECT c.emotionList From Content c WHERE c.type=:type")
    List<Content> findGenreByEmotion(@Param("emotion") String emotion,
                                @Param("type") ContentType type, @Param("size") int size);


    // 감정에 따른 장르
    @Query("SELECT c.genreList From Content c WHERE c.emotionList = :emotionVar")
    List<Content> findByEmotion(@Param("emotion") String emotionVar,
                                     @Param("type") ContentType type, @Param("size") int size);


    // 누구와 보는지에 따른 연령 필터링
    List<Content> findByWithWhom(@Param("withWhom") String withWhom);

    @Query("SELECT c FROM Content c WHERE c.type = :type AND :emotion MEMBER OF c.emotionList")
    List<Content> findByTypeAndEmotionAndWith(@Param("type") ContentType type, @Param("emotion") String emotion, @Param("with") String with);
    */




