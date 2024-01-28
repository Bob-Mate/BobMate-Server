package com.umc.bobmate.like.domain.repository;

import com.umc.bobmate.content.domain.Content;
import com.umc.bobmate.like.domain.Likes;
import com.umc.bobmate.member.domain.Member;
import com.umc.bobmate.menu.domain.Menu;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Likes, Long> {

    // ContentId로 좋아요 개수 조회
    long countByContentId(Long contentId);

    Optional<Likes> findByMemberAndContent(Member member, Content content);
    Optional<Likes> findByMemberAndMenu(Member member, Menu menu);
    List<Likes> findByMember(Member member);
}
