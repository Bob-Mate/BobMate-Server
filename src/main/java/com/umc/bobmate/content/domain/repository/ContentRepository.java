package com.umc.bobmate.content.domain.repository;

import com.umc.bobmate.content.domain.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content, Long> {
}
