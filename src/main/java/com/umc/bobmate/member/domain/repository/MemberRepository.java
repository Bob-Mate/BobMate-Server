package com.umc.bobmate.member.domain.repository;

import com.umc.bobmate.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
