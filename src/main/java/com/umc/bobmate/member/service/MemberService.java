package com.umc.bobmate.member.service;

import com.umc.bobmate.member.domain.Member;
import com.umc.bobmate.member.domain.repository.MemberRepository;
import java.util.List;
import java.util.Optional;

import com.umc.bobmate.comment.domain.Comment;
import com.umc.bobmate.comment.domain.repository.CommentRepository;
import com.umc.bobmate.global.apiPayload.exception.GeneralException;
import com.umc.bobmate.global.s3.DirName;
import com.umc.bobmate.global.s3.S3Service;
import com.umc.bobmate.login.jwt.util.AuthTokensGenerator;
import com.umc.bobmate.member.domain.repository.CustomMemberRepository;
import com.umc.bobmate.member.dto.request.CommentUploadRequest;
import com.umc.bobmate.member.dto.request.EditRequest;
import com.umc.bobmate.member.dto.request.PreferenceUploadRequest;
import com.umc.bobmate.member.dto.response.CommentResponse;
import com.umc.bobmate.member.dto.response.EditPageResponse;
import com.umc.bobmate.member.dto.response.PreferenceResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static com.umc.bobmate.global.apiPayload.code.status.ErrorStatus.INVALID_MEMBER_ID;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final CustomMemberRepository customMemberRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private final CommentRepository commentRepository;
    private final S3Service s3Service;

    public Member findById(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new GeneralException(INVALID_MEMBER_ID));
    }

    @Transactional
    public void saveContentPreference(final PreferenceUploadRequest preferenceRequest) {
        final Member loginMember = authTokensGenerator.getLoginMember();
        loginMember.modifyPreference(preferenceRequest.getPreferenceList());
    }

    public PreferenceResponse getContentPreference() {
        final Member loginMember = authTokensGenerator.getLoginMember();
        final List<String> preferenceList = customMemberRepository.findPreferencesByMember(loginMember.getId());
        return new PreferenceResponse(
                loginMember.getName(),
                preferenceList
        );
    }

    @Transactional
    public void saveComment(final CommentUploadRequest uploadRequest) {
        final Member loginMember = authTokensGenerator.getLoginMember();

        final Comment comment = Comment.builder()
                .member(loginMember)
                .genre(uploadRequest.getGenre())
                .food(uploadRequest.getFood())
                .emotion(uploadRequest.getEmotion())
                .build();

        commentRepository.save(comment);
    }

    public CommentResponse getComment() {
        final Member loginMember = authTokensGenerator.getLoginMember();
        final Comment comment = commentRepository.findFirstByMemberOrderByCreatedDateDesc(loginMember);
        return new CommentResponse(comment);
    }

    public EditPageResponse getEditPage() {
        final Member loginMember = authTokensGenerator.getLoginMember();
        return new EditPageResponse(loginMember);
    }

    @Transactional
    public void editProfile(MultipartFile multipartFile, EditRequest request) {
        final Member loginMember = authTokensGenerator.getLoginMember();
        if (multipartFile.isEmpty()) {
            loginMember.deleteProfileImage();
        } else {
            final String imgUrl = s3Service.upload(multipartFile, DirName.MEMBER);
            loginMember.modifyProfileImage(imgUrl);
        }
        loginMember.modifyName(request.getName());
    }


    public Boolean checkDuplicate(String name) {
        return memberRepository.existsByName(name);
    }
}
