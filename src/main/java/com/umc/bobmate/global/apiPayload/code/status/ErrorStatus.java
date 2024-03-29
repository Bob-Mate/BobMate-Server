package com.umc.bobmate.global.apiPayload.code.status;

import com.umc.bobmate.global.apiPayload.code.BaseErrorCode;
import com.umc.bobmate.global.apiPayload.code.ErrorReasonDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {
    // global
    _INTERNAL_SERVER_ERROR(INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    // member
    INVALID_MEMBER_ID(BAD_REQUEST, "MEMBER4001", "유효하지 않은 멤버의 아이디입니다."),
    
    // login
    EXPIRED_TOKEN(BAD_REQUEST, "LOGIN4001", "만료된 토큰입니다. 토큰을 다시 발급하세요."),

    // image
    CONVERT_FILE_ERROR(INTERNAL_SERVER_ERROR, "IMAGE5001", "서버 파일 변환 에러, 관리자에게 문의 바랍니다."),
    IO_EXCEPTION(INTERNAL_SERVER_ERROR, "IMAGE5001", "서버 IO 에러, 관리자에게 문의 바랍니다."),
    S3_UPLOAD_ERROR(INTERNAL_SERVER_ERROR, "IMAGE5001", "서버 S3 접근 에러, 관리자에게 문의 바랍니다."),
    DELETE_FILE_ERROR(INTERNAL_SERVER_ERROR, "IMAGE5001", "서버 파일 삭제 에러, 관리자에게 문의 바랍니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDto getReason() {
        return ErrorReasonDto.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDto getReasonHttpStatus() {
        return ErrorReasonDto.builder()
                .httpStatus(httpStatus)
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }
}
