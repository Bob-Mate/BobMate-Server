package com.umc.bobmate.comment.domain.repository;

import com.umc.bobmate.comment.domain.Comment;
import com.umc.bobmate.content.domain.Emotion;
import com.umc.bobmate.content.domain.Genre;
import com.umc.bobmate.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findFirstByMemberOrderByCreatedDateDesc(Member member);

    @Query("SELECT c FROM Comment c WHERE c.emotion = :emotion AND c.food = :food")
    List<Comment> findCommentsByEmotionAndFood(@Param("emotion") Emotion emotion, @Param("food") String food);


}
