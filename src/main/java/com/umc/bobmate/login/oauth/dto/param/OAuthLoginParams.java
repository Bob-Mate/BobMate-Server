package com.umc.bobmate.login.oauth.dto.param;

import com.umc.bobmate.member.domain.OAuthProvider;
import org.springframework.util.MultiValueMap;

public interface OAuthLoginParams {
    OAuthProvider oAuthProvider();

    MultiValueMap<String, String> makeBody();
}
