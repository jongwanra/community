package com.community.member.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.community.member.infrastructure.entity.MemberEntity;

public interface MemberJpaRepository extends JpaRepository<MemberEntity, Long> {
}
