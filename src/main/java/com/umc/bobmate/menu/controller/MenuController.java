package com.umc.bobmate.menu.controller;

import com.umc.bobmate.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;
}
