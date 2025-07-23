package com.community.member.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.community.member.infrastructure.entity.MemberJpaEntity;

public interface MemberJpaRepository extends JpaRepository<MemberJpaEntity, Long> {
}
