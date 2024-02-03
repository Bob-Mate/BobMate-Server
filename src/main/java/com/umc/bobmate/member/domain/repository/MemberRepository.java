package com.umc.bobmate.member.domain.repository;

import com.umc.bobmate.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import static com.umc.bobmate.common.BaseEntityStatus.DELETED;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findBySocialId(String socialId);

<<<<<<< HEAD
=======
    default void deleteMember(Member member) {
        member.setStatus(DELETED);
    }
>>>>>>> ff82077c6f0f16f906e47ae39d6233050ac19f2a
}
