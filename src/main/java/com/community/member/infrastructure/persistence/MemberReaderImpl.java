package com.community.member.infrastructure.persistence;

import static com.community.member.infrastructure.entity.QMemberEntity.*;

import org.springframework.stereotype.Repository;

import com.community.member.domain.repository.MemberReader;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

@Repository
public class MemberReaderImpl implements MemberReader {
	private final JPAQueryFactory queryFactory;

	public MemberReaderImpl(EntityManager entityManager) {
		this.queryFactory = new JPAQueryFactory(entityManager);
	}

	@Override
	public boolean existsByEmail(String email) {
		if (email == null || email.isBlank()) {
			return false;
		}

		return queryFactory
			.select(memberEntity.id)
			.from(memberEntity)
			.where(memberEntity.email.eq(email))
			.fetchFirst() != null;
	}

	@Override
	public boolean existsByNickname(String nickname) {
		if (nickname == null || nickname.isBlank()) {
			return false;
		}

		return queryFactory
			.select(memberEntity.id)
			.from(memberEntity)
			.where(memberEntity.nickname.eq(nickname))
			.fetchFirst() != null;
	}
}
