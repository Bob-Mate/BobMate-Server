package com.umc.bobmate.like.domain.repository;

import com.umc.bobmate.content.domain.Content;
import com.umc.bobmate.like.domain.Likes;
import com.umc.bobmate.member.domain.Member;
import com.umc.bobmate.menu.domain.Menu;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LikeRepository extends JpaRepository<Likes, Long> {

    // ContentId로 좋아요 개수 조회
    long countByContentId(Long contentId);

    Optional<Likes> findByMemberAndContent(Member member, Content content);
    Optional<Likes> findByMemberAndMenu(Member member, Menu menu);
    List<Likes> findByMember(Member member);

    @Modifying
    @Query("update Likes l set l.status = 'DELETED' " +
            "where l.member = :member ")
    public void deleteByMember(@Param("member") Member member);

}
