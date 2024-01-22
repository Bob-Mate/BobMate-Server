package com.umc.bobmate.like.domain.repository;

import com.umc.bobmate.like.domain.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Likes, Long> {
}
