package com.umc.bobmate.comment.domain.repository;

import com.umc.bobmate.comment.domain.Comment;
import com.umc.bobmate.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findFirstByMemberOrderByCreatedDateDesc(Member member);
}
