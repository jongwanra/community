package com.community.member.infrastructure.persistence;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.community.member.application.repository.MemberRepository;
import com.community.member.domain.Member;
import com.community.member.infrastructure.entity.MemberJpaEntity;

@Repository
public class MemberRepositoryImpl implements MemberRepository {
	private final MemberJpaRepository memberJpaRepository;

	public MemberRepositoryImpl(MemberJpaRepository memberJpaRepository) {
		this.memberJpaRepository = memberJpaRepository;
	}

	@Override
	public Optional<Member> findById(long memberId) {
		return memberJpaRepository.findById(memberId)
			.map(MemberJpaEntity::toDomain);
	}

	@Override
	public Member save(Member member) {
		MemberJpaEntity memberJpaEntity = MemberJpaEntity.from(member);
		return memberJpaRepository.save(memberJpaEntity)
			.toDomain();
	}

	@Override
	public boolean existsByNickname(String nickname) {
		return false;
	}

	@Override
	public boolean existsByEmail(String email) {
		return false;
	}

	@Override
	public boolean existsById(Long memberId) {
		return false;
	}
}
