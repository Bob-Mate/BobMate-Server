package com.umc.bobmate.login.oauth.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UnLinkResponse {
    private String id;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("result")
    private String result;
}
