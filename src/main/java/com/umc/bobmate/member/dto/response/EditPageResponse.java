package com.umc.bobmate.member.dto.response;

import com.umc.bobmate.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditPageResponse {
    private String profileImage;
    private String nickname;

    public EditPageResponse(Member member) {
        this.profileImage = member.getImageUrl();
        this.nickname = member.getName();
    }
}
