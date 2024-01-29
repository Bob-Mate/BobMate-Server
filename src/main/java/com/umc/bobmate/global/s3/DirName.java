package com.umc.bobmate.global.s3;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DirName {
    MEMBER("member");

    private final String dirName;
}
