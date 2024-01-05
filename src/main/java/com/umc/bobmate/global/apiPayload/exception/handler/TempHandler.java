package com.umc.bobmate.global.apiPayload.exception.handler;

import com.umc.bobmate.global.apiPayload.code.BaseErrorCode;
import com.umc.bobmate.global.apiPayload.exception.GeneralException;

public class TempHandler extends GeneralException {
    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
