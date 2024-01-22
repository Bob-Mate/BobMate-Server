package com.umc.bobmate.content.controller;

import com.umc.bobmate.content.dto.ContentResponse;
import com.umc.bobmate.content.service.ContentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umc.bobmate.content.domain.ContentType;
import com.umc.bobmate.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/contents")
@RequiredArgsConstructor
public class ContentController {
    private final ContentService contentService;


    @GetMapping("")
    public ApiResponse<List<ContentResponse>> getAllContents(@RequestParam(name = "section") int section) {

        if (section == 0) { // 0: VIDEO
            return ApiResponse.onSuccess(contentService.getTop3Contents(ContentType.VIDEO));
        } else if (section == 1) { // 1: TEXT
            return ApiResponse.onSuccess(contentService.getTop3Contents(ContentType.TEXT));
        } else {
            System.out.println("섹션값 오류");
            throw new IllegalArgumentException("Invalid");
        }
    }


    //    @GetMapping("/video")
    //    public ApiResponse<List<ContentResponse>> getTop3VideoContents() {
    //        return ApiResponse.onSuccess(contentService.getAllVideoContents());
    //    }
    //
    //    @GetMapping("/text")
    //    public ApiResponse<List<ContentResponse>> getTop3TextContents() {
    //        return ApiResponse.onSuccess(contentService.getAllTextContents());
    //    }

}
