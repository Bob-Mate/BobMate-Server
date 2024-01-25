package com.umc.bobmate.like.domain.repository;

import com.umc.bobmate.like.domain.Likes;
import com.umc.bobmate.member.domain.Member;
import com.umc.bobmate.menu.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Likes, Long> {
    List<Likes> findByMemberId(Long MemberId);



    @Query("SELECT jjim FROM Likes jjim " +
            "WHERE jjim.member = :member")
    List<Likes> findLikeHistoriesWithMemberAndPostByMember(@Param("member") Member member);

    @Query("SELECT menu FROM Menu menu WHERE menu.id = :MenuId and .member.id = :memberId")
    Optional<Menu> findLikeHistoriesByPostId(@Param("menuId") Long postId, @Param("memberId") Long memberId);
}
