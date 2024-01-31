package com.umc.bobmate.like.domain.repository;

import com.umc.bobmate.like.domain.Likes;
import com.umc.bobmate.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LikeRepository extends JpaRepository<Likes, Long> {

    @Modifying
    @Query("update Likes l set l.status = 'DELETED' " +
            "where l.member = :member ")
    public void deleteByMember(@Param("member") Member member);
}
