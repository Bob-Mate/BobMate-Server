package com.umc.bobmate.content.domain.repository;

import com.umc.bobmate.content.domain.Content;
import com.umc.bobmate.content.domain.ContentType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContentRepository extends JpaRepository<Content, Long> {
    List<Content> findByType(ContentType type);

    @Query("SELECT c FROM Content c " +
            "LEFT JOIN c.likes l " +
            "WHERE c.type = :contentType " +
            "GROUP BY c.id " +
            "ORDER BY COUNT(l) DESC")
    List<Content> findTop3ContentsByLikesAndType(@Param("contentType") ContentType contentType);


    //    @Query("SELECT c FROM Content c " +
    //            "LEFT JOIN FETCH c.likes " +
    //            "GROUP BY c.id " +
    //            "ORDER BY COUNT(c.likes) DESC")
    //    List<Content> findTop3ContentsByLikes();
}
