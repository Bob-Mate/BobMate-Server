package com.umc.bobmate.member.domain.repository;

import com.umc.bobmate.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomMemberRepository extends JpaRepository<Member, Long> {
    @Query(value = "select p.memo from preference p where p.member_id = :memberId ", nativeQuery = true)
    List<String> findPreferencesByMember(@Param("memberId") final Long memberId);
}
