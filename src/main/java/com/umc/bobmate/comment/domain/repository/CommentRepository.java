package com.umc.bobmate.comment.domain.repository;

import com.umc.bobmate.comment.domain.Comment;
import com.umc.bobmate.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findFirstByMemberOrderByCreatedDateDesc(Member member);

    @Modifying
    @Query("update Comment c set c.status = 'DELETED' " +
            "where c.member = :member")
    void deleteByMember(@Param("member") Member loginMember);
}
