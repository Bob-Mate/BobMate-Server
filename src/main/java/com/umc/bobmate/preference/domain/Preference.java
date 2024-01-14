package com.umc.bobmate.preference.domain;

import static lombok.AccessLevel.PROTECTED;

import com.umc.bobmate.common.BaseEntity;
import com.umc.bobmate.member.domain.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
public class Preference extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "preference_id")
    private Long id;

    private String memo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}

/** 값타입 버전
 * @Getter
 * @Embeddable
 * public class Preference extends BaseEntity {
 *
 *     @Id
 *     @GeneratedValue(strategy = GenerationType.IDENTITY)
 *     @Column(name = "preference_id")
 *     private Long id;
 *
 *     private String memo;
 *
 *     @ManyToOne
 *     @JoinColumn(name = "member_id")
 *     private Member member;
 *
 *     protected Preference() {
 *     }
 *
 *     public Preference(Long id, String memo, Member member) {
 *         this.id = id;
 *         this.memo = memo;
 *         this.member = member;
 *     }
 *
 * }
 */