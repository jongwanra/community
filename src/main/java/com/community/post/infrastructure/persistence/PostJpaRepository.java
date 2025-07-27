package com.community.post.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.community.post.infrastructure.entity.PostEntity;

public interface PostJpaRepository extends JpaRepository<PostEntity, Long> {
}
