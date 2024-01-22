package com.umc.bobmate.content.controller;

import com.umc.bobmate.content.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ContentController {
    private final ContentService contentService;
}
