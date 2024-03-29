package com.umc.bobmate.global.apiPayload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.umc.bobmate.global.apiPayload.code.BaseCode;
import com.umc.bobmate.global.apiPayload.code.status.SuccessStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.umc.bobmate.global.apiPayload.code.status.SuccessStatus.*;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class ApiResponse<T> {

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;
    private final String code;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

    // success
    public static <T> ApiResponse<T> onSuccess() {
        return new ApiResponse<>(true, _NO_CONTENT.getCode(), _NO_CONTENT.getMessage(), null);
    }

    public static <T> ApiResponse<T> onSuccess(T result) {
        return new ApiResponse<>(true, _OK.getCode(), _OK.getMessage(), result);
    }

    public static <T> ApiResponse<T> of(BaseCode code) {
        return new ApiResponse<>(true, code.getReasonHttpStatus().getCode(), code.getReasonHttpStatus().getMessage(), null);
    }

    public static <T> ApiResponse<T> of(BaseCode code, T result) {
        return new ApiResponse<>(true, code.getReasonHttpStatus().getCode(), code.getReasonHttpStatus().getMessage(), result);
    }

    // fail
    public static <T> ApiResponse<T> onFailure(String code, String message, T data) {
        return new ApiResponse<>(false, code, message, data);
    }

}
