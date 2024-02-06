package com.umc.bobmate.comment.domain.repository;

import com.umc.bobmate.comment.domain.Comment;
import com.umc.bobmate.content.domain.Content;
import com.umc.bobmate.content.domain.ContentType;
import com.umc.bobmate.member.domain.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findFirstByMemberOrderByCreatedDateDesc(Member member);

    @Modifying
    @Query("update Comment c set c.status = 'DELETED' " +
            "where c.member = :member")
    void deleteByMember(@Param("member") Member loginMember);

    @Query("SELECT DISTINCT c FROM Comment c GROUP BY c.food, c.emotion ORDER BY COUNT(c.genre) DESC LIMIT 4")
    List<Comment> findMostFrequentCommentList();

    Comment findCommentById(Long id);

    @Query("SELECT c FROM Content c WHERE c.type = :type GROUP BY c.id ORDER BY c.id DESC")
    List<Content> findLast5ContentsByType(@Param("type") ContentType type, Pageable pageable);
}
