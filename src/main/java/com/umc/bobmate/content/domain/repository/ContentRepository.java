package com.umc.bobmate.content.domain.repository;

import com.umc.bobmate.comment.domain.Comment;
import com.umc.bobmate.content.domain.Content;
import com.umc.bobmate.content.domain.ContentType;
import java.util.List;

import com.umc.bobmate.evaluation.domain.Evaluation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContentRepository extends JpaRepository<Content, Long> {

    @Query("SELECT c FROM Content c WHERE c.type = :contentType ORDER BY c.likesCount DESC")
    List<Content> findTop3ByOrderByLikesCountDesc(@Param("contentType") ContentType contentType, Pageable pageable);

    // 컨텐츠에서 type 기준으로 컨텐츠 가져오기
    @Query("SELECT c FROM Content c WHERE (c.type = :type and c.status='ACTIVE')")
    List<Content> findByType(@Param("type") ContentType type);

    // 추천누른 컨텐츠 다시 추천해주는 쿼리문 작성
    @Query("SELECT c FROM Content c " +
            "LEFT JOIN Evaluation e ON c.id = e.content.id WHERE (c.type=:type and e.status!='DELETED' and e.isGood=true)")
    List<Content> recommendAgain(@Param("type") ContentType type);

    // 비추천한거 제외한 것
//    @Query("SELECT c FROM Content c " +
//        "LEFT JOIN Evaluation e ON c.id = e.content.id WHERE (c.type=:type AND e.isGood = false and e.status='ACTIVE')")
    @Query("SELECT c FROM Content c " +
            "LEFT JOIN Evaluation e ON c.id = e.content.id WHERE (e.member.id=:id and c.type=:type AND e.isGood = false AND e.status='ACTIVE')")
    //@Query("SELECT e From Evaluation e WHERE e.member.id=:id And e.content.type=:type")
    List<Content> findBadEvaluation(@Param("id") Long id, @Param("type") ContentType type);


    @Query("SELECT c FROM Comment c WHERE c.id=:id")
    Comment findGenreById(@Param("id") Long id);

    @Query("SELECT c FROM Content c WHERE c.id NOT IN :excludedContentIds and c.type=:type")
    List<Content> findOtherContents(List<Long> excludedContentIds, ContentType type);

//    @Query("SELECT c FROM Content c WHERE c.genreList COMEDY or 'ACTION' or 'ROMANCE' AND c.type = 'TEXT'")
//    List<Long> findContentIdsWithGenres();


}






