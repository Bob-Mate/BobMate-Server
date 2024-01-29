package com.umc.bobmate.global.apiPayload.code.status;

import com.umc.bobmate.global.apiPayload.code.BaseCode;
import com.umc.bobmate.global.apiPayload.code.ReasonDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {
    // common
    _OK(HttpStatus.OK, "COMMON200","성공입니다."),
    _NO_CONTENT(HttpStatus.NO_CONTENT, "COMMON201","성공입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDto getReason() {
        return ReasonDto.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .build();
    }

    @Override
    public ReasonDto getReasonHttpStatus() {
        return ReasonDto.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .httpStatus(httpStatus)
                .build();
    }
}
