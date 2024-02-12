package com.umc.bobmate.member.domain.repository;

import com.umc.bobmate.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import static com.umc.bobmate.common.BaseEntityStatus.DELETED;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findBySocialId(String socialId);

    Boolean existsByName(String name);

    default void deleteMember(Member member) {
        member.setStatus(DELETED);
    }


}
