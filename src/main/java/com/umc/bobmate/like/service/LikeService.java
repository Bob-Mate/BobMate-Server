package com.umc.bobmate.like.service;

import com.umc.bobmate.content.domain.Content;
import com.umc.bobmate.content.domain.repository.ContentRepository;
import com.umc.bobmate.content.dto.ContentDailyResponse;
import com.umc.bobmate.like.domain.Likes;
import com.umc.bobmate.like.domain.repository.LikeRepository;
import com.umc.bobmate.member.domain.Member;
import com.umc.bobmate.member.domain.repository.MemberRepository;
import com.umc.bobmate.menu.domain.Menu;
import com.umc.bobmate.menu.domain.repository.MenuRepository;

import com.umc.bobmate.menu.dto.MenuResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class LikeService {


    private final LikeRepository likeRepository;
    private final MemberRepository memberRepository;
    private final ContentRepository contentRepository;
    private final MenuRepository menuRepository;

    public void likeContent(Long memberId, Long contentId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found with ID: " + memberId));

        Content content = contentRepository.findById(contentId)
                .orElseThrow(() -> new EntityNotFoundException("Content not found with ID: " + contentId));

        Likes existingLike = likeRepository.findByMemberAndContent(member, content).orElse(null);

        if (existingLike == null) {
            // 좋아요를 누르지 않은 경우에만 새로운 Likes 엔티티를 생성
            Likes like = new Likes();
            like.setMember(member);
            like.setContent(content);
            likeRepository.save(like);
        }

        // Content에 좋아요 개수 반영
        long likesCount = likeRepository.countByContentId(contentId);
        content.setLikesCount(likesCount);
        contentRepository.save(content);
    }

    public void unlikeContent(Long memberId, Long contentId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found with ID: " + memberId));

        Content content = contentRepository.findById(contentId)
                .orElseThrow(() -> new EntityNotFoundException("Content not found with ID: " + contentId));

        // 좋아요 엔티티 조회
        Likes like = likeRepository.findByMemberAndContent(member, content)
                .orElseThrow(() -> new EntityNotFoundException("Like not found for Member and Content"));

        // 좋아요 취소
        likeRepository.delete(like);

        // Content에 좋아요 개수 반영
        long likesCount = likeRepository.countByContentId(contentId);

        updateLikesCountInNewTransaction(contentId, likesCount);
    }

    // 새로운 트랜잭션 열어서 좋아요 개수 업데이트
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateLikesCountInNewTransaction(Long contentId, long likesCount) {
        Content content = contentRepository.findById(contentId)
                .orElseThrow(() -> new EntityNotFoundException("Content not found with ID: " + contentId));

        content.setLikesCount(likesCount);
        contentRepository.save(content);
    }

    public void likeMenu(Long memberId, Long menuId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found with ID: " + memberId));

        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new EntityNotFoundException("Menu not found with ID: " + menuId));

        Likes existingLike = likeRepository.findByMemberAndMenu(member, menu).orElse(null);

        if (existingLike == null) {
            // 좋아요를 누르지 않은 경우에만 새로운 Likes 엔티티를 생성
            Likes like = new Likes();
            like.setMember(member);
            like.setMenu(menu);
            likeRepository.save(like);
        }

    }

    public void unlikeMenu(Long memberId, Long menuId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found with ID: " + memberId));

        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new EntityNotFoundException("Menu not found with ID: " + menuId));

        // 좋아요 엔티티 조회
        Likes like = likeRepository.findByMemberAndMenu(member, menu)
                .orElseThrow(() -> new EntityNotFoundException("Likes not found for Member ID: " + memberId + " and Menu ID: " + menuId));

        // 좋아요 취소
        likeRepository.delete(like);
    }

    public List<ContentDailyResponse> getLikedContents(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found with ID: " + memberId));

        List<Likes> likes = likeRepository.findByMember(member);

        List<ContentDailyResponse> likedContents = likes.stream()
                .map(like -> mapContentToResponse(like.getContent()))
                .collect(Collectors.toList());

        return likedContents;
    }

    public List<MenuResponse> getLikedMenus(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found with ID: " + memberId));

        List<Likes> likes = likeRepository.findByMember(member);

        List<MenuResponse> likedMenus = likes.stream()
                .map(like -> mapMenuToResponse(like.getMenu()))
                .collect(Collectors.toList());

        return likedMenus;
    }


    private ContentDailyResponse mapContentToResponse(Content content) {
        return ContentDailyResponse.builder()
                .contentId(content.getId())
                .name(content.getName())
                .imgUrl(content.getImgUrl())
                .linkUrl(content.getLinkUrl())
                .build();
    }

    private MenuResponse mapMenuToResponse(Menu menu) {
        return MenuResponse.builder()
                .menuId(menu.getId())
                .menuType(menu.getType())
                .name(menu.getName())
                .imgUrl(menu.getImgUrl())
                .build();
    }

}

