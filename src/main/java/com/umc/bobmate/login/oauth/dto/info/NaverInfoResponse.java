package com.umc.bobmate.login.oauth.dto.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.umc.bobmate.member.domain.OAuthProvider;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class NaverInfoResponse implements OAuthInfoResponse {

    @JsonProperty("response")
    private Response response;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Response {
        private String nickname;

        @JsonProperty("id")
        private String socialId;

        @JsonProperty("profile_image")
        private String profileImage;
    }

    @Override
    public String getSocialId() {
        return response.socialId;
    }

    @Override
    public String getProfileImage() {
        return response.profileImage;
    }

    @Override
    public String getNickname() {
        return response.nickname;
    }

    @Override
    public OAuthProvider getOAuthProvider() {
        return OAuthProvider.NAVER;
    }
}
