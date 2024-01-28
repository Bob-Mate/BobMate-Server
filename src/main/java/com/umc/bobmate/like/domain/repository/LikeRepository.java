package com.umc.bobmate.like.domain.repository;

import com.umc.bobmate.content.domain.Content;
import com.umc.bobmate.like.domain.LikeType;
import com.umc.bobmate.like.domain.Likes;
import com.umc.bobmate.member.domain.Member;
import com.umc.bobmate.menu.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Likes, Long> {
    //List<Likes> findByMemberAndMenu(Long MemberId);
    Optional<Likes> findByMemberAndContentAndType(Member member, Content content, LikeType likeType);

    Optional<Likes> findByMemberAndMenuAndType(Member member, Menu menu, LikeType likeType);

    List<Likes> findByMemberAndMenu(Member member, Menu menu);

    List<Likes> findByMemberAndContent(Member member, Content content);

    List<Likes> findByMemberAndType(Member member, LikeType type);


//    @Query("SELECT l FROM Likes l JOIN FETCH l.member m LEFT JOIN FETCH l.menu mn WHERE mn.id = :menuId AND m.id = :memberId")
//    List<Likes> findByMenuIdAndMemberId(@Param("menuId") Long menuId, @Param("memberId") Long memberId);
//
//
//    @Query("SELECT l FROM Likes l JOIN FETCH l.member m LEFT JOIN FETCH l.menu mn LEFT JOIN FETCH l.content c WHERE m.id = :memberId")
//    List<Likes> findLikesByMemberId(@Param("memberId") Long memberId);
//
//
//    @Query("SELECT l FROM Likes l JOIN FETCH l.member m LEFT JOIN FETCH l.content c WHERE c.id = :contentId AND m.id = :memberId")
//    List<Likes> findByContentIdAndMemberId(@Param("contentId") Long contentId, @Param("memberId") Long memberId);
//
//    @Query("SELECT lk FROM Likes lk JOIN FETCH lk.member ")
//    List<Likes>findMenuLikesByMemberId(@Param("memberId") Long memberId, @Param("menuId") Menu menuId);
//    findContentLikesByMemberId(memberId);
}
