package com.umc.bobmate.menu.domain.repository;

import com.umc.bobmate.menu.domain.Menu;
import com.umc.bobmate.menu.domain.MenuType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByType(MenuType type);
}
