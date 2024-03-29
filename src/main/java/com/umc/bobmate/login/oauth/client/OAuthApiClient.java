package com.umc.bobmate.login.oauth.client;

import com.umc.bobmate.login.oauth.dto.response.OAuthInfoResponse;
import com.umc.bobmate.login.oauth.dto.param.OAuthLoginParams;
import com.umc.bobmate.member.domain.OAuthProvider;

public interface OAuthApiClient {
    OAuthProvider oAuthProvider();

    String requestAccessToken(OAuthLoginParams params);

    OAuthInfoResponse requestOauthInfo(String accessToken);

    void requestUnlink(String socialAccessToken);
}
