package com.community.post.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.community.post.infrastructure.entity.PostJpaEntity;

public interface PostJpaRepository extends JpaRepository<PostJpaEntity, Long> {
}
