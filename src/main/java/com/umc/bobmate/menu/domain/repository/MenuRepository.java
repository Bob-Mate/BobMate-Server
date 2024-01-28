package com.umc.bobmate.menu.domain.repository;

import com.umc.bobmate.menu.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
