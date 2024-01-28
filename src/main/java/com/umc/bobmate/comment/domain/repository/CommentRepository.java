package com.umc.bobmate.comment.domain.repository;

import com.umc.bobmate.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
