package com.umc.bobmate.global.apiPayload.exception;

import com.umc.bobmate.global.apiPayload.code.BaseErrorCode;
import com.umc.bobmate.global.apiPayload.code.ErrorReasonDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {
    private BaseErrorCode errorCode;

    public ErrorReasonDto getErrorReason() {
        return this.errorCode.getReason();
    }

    public ErrorReasonDto getErrorReasonHttpStatus() {
        return this.errorCode.getReasonHttpStatus();
    }
}
