package com.umc.bobmate.login.oauth.dto.response;

import com.umc.bobmate.member.domain.OAuthProvider;

public interface OAuthInfoResponse {

    String getSocialId();

    String getProfileImage();

    String getNickname();

    OAuthProvider getOAuthProvider();

    String getAccessToken();
}
