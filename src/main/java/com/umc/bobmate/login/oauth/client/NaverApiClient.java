package com.umc.bobmate.login.oauth.client;

import com.umc.bobmate.login.oauth.dto.param.OAuthLoginParams;
import com.umc.bobmate.login.oauth.dto.response.NaverInfoResponse;
import com.umc.bobmate.login.oauth.dto.response.OAuthInfoResponse;
import com.umc.bobmate.login.oauth.dto.response.UnLinkResponse;
import com.umc.bobmate.login.oauth.dto.token.NaverTokens;
import com.umc.bobmate.member.domain.OAuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class NaverApiClient implements OAuthApiClient {

    private static final String GRANT_TYPE = "authorization_code";
    private static final String DELETE_GRANT_TYPE = "delete";

    @Value("${oauth.naver.url.auth}")
    private String authUrl;

    @Value("${oauth.naver.url.api}")
    private String apiUrl;

    @Value("${oauth.naver.client-id}")
    private String clientId;

    @Value("${oauth.naver.secret}")
    private String clientSecret;

    private final RestTemplate restTemplate;

    @Override
    public OAuthProvider oAuthProvider() {
        return OAuthProvider.NAVER;
    }

    @Override
    public String requestAccessToken(OAuthLoginParams params) {
        String url = authUrl + "/oauth2.0/token";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = params.makeBody();
        body.add("grant_type", GRANT_TYPE);
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);

        HttpEntity<?> request = new HttpEntity<>(body, httpHeaders);

        NaverTokens response = restTemplate.postForObject(url, request, NaverTokens.class);

        assert response != null;
        return response.getAccessToken();
    }

    @Override
    public OAuthInfoResponse requestOauthInfo(String accessToken) {
        String url = apiUrl + "/v1/nid/me";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.set("Authorization", "Bearer " + accessToken);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();

        HttpEntity<?> request = new HttpEntity<>(body, httpHeaders);

        final NaverInfoResponse naverInfoResponse = restTemplate.postForObject(url, request, NaverInfoResponse.class);
        if (naverInfoResponse == null) throw new AssertionError();
        naverInfoResponse.setAccessToken(accessToken);

        return naverInfoResponse;
    }

    @Override
    public void requestUnlink(String socialAccessToken) {
        String url = authUrl + "/oauth2.0/token";
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("access_token", socialAccessToken);
        body.add("grant_type", DELETE_GRANT_TYPE);
        body.add("service_provider", "NAVER");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        final HttpEntity<?> request = new HttpEntity<>(body, httpHeaders);

        final UnLinkResponse response = restTemplate.postForObject(url, request, UnLinkResponse.class);
        System.out.println("삭제된 토큰 확인하기:: " + response.getAccessToken());
    }
}
