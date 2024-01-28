package com.umc.bobmate.like.service;

import com.umc.bobmate.content.domain.Content;
import com.umc.bobmate.content.domain.repository.ContentRepository;
import com.umc.bobmate.content.dto.ContentResponseDTO;
import com.umc.bobmate.like.domain.LikeType;
import com.umc.bobmate.like.domain.Likes;
import com.umc.bobmate.like.domain.repository.LikeRepository;
import com.umc.bobmate.member.domain.Member;
import com.umc.bobmate.member.domain.repository.MemberRepository;
import com.umc.bobmate.menu.domain.Menu;
import com.umc.bobmate.menu.domain.repository.MenuRepository;
import com.umc.bobmate.menu.dto.MenuResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.relational.core.sql.Like;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikeService {

    @Autowired
    private LikeRepository likesRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private ContentRepository contentRepository;


    // Likes에 memberId, menuId, type(Menu)에 따른 create 함수
    public Likes likeMenu(Long memberId, Long menuId, LikeType type) {

        // member id로 member 조회 - optional 리턴타입이니까 orElse(null) 추가
        Member member = memberRepository.findById(memberId).orElse(null);

        // 메뉴 ID로 Menu를 조회
        Menu menu = menuRepository.findById(menuId).orElse(null);

        if (likesRepository.findByMemberAndMenuAndType(member, menu, LikeType.MENU).isPresent()) {
            throw new RuntimeException("Member already liked the menu with ID: " + menuId);
        }

        Likes like = Likes.builder()
                .type(type)
                .member(member)
                .menu(menu)
                .build();

        return likesRepository.save(like);
    }

    // Likes에 memberId, contentId, type(Menu)에 따른 create 함수
    public Likes likeContent(Long memberId, Long contentId, LikeType type) {

        // member id로 member 조회 - optional 리턴타입이니까 orElse(null) 추가
        Member member = memberRepository.findById(memberId).orElse(null);

        // 메뉴 ID로 Menu를 조회
        Content content = contentRepository.findById(contentId).orElse(null);

        if (likesRepository.findByMemberAndContentAndType(member, content, LikeType.CONTENT).isPresent()) {
            throw new RuntimeException("Member already liked the content with ID: " + contentId);
        }

        Likes like = Likes.builder()
                .type(type)
                .member(member)
                .content(content)
                .build();

        return likesRepository.save(like);
    }

    public List<Likes> getLikesByMemberAndType(Long memberId, LikeType type) {
        Member member = memberRepository.findById(memberId).orElse(null);
        return likesRepository.findByMemberAndType(member, type);
    }

    public List<Likes> getMenuLikesByMember(Long memberId) {
        return getLikesByMemberAndType(memberId, LikeType.MENU);
    }

    public List<Likes> getContentLikesByMember(Long memberId) {
        return getLikesByMemberAndType(memberId, LikeType.CONTENT);
    }


//    public List<Likes> getLikesByMemberAndMenu(Member member, Menu menu) {
//        return likesRepository.findByMemberAndMenu(member, menu);
//    }
//
//    public List<Likes> getLikesByMemberAndContent(Member member, Content content) {
//        return likesRepository.findByMemberAndContent(member, content);
//    }


}


/*
public List<MenuResponseDTO> findMenuLikesByMemberId(Long memberId) {
        List<Likes> menuLikes = likesRepository.findMenuLikesByMemberId(memberId);
        return menuLikes.stream()
                .map(like -> MenuConverter.toMenuResponseDTO(like.getMenu()))
                .collect(Collectors.toList());
    }

    public List<ContentResponseDTO> findContentLikesByMemberId(Long memberId) {
        List<Likes> contentLikes = likesRepository.findContentLikesByMemberId(memberId);
        return contentLikes.stream()
                .map(like -> ContentConverter.toContentResponseDTO(like.getContent()))
                .collect(Collectors.toList());
    }

    public List<Likes> getLikesByMemberAndMenu(Member member, Menu menu) {
        return likesRepository.findByMemberAndMenu(member, menu);
    }
 */