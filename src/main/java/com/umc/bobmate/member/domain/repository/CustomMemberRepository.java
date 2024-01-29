package com.umc.bobmate.member.domain.repository;

import com.umc.bobmate.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomMemberRepository extends JpaRepository<Member, Long> {
    @Query(value = "select p.memo from preference p where p.member_id = :memberId ", nativeQuery = true)
    List<String> findPreferencesByMember(@Param("memberId") final Long memberId);
}
