package com.community.member.infrastructure.persistence;

import org.springframework.stereotype.Repository;

import com.community.global.enums.ErrorCode;
import com.community.global.exception.CommunityException;
import com.community.member.application.port.MemberRepository;
import com.community.member.domain.Member;
import com.community.member.infrastructure.entity.MemberJpaEntity;

@Repository
public class MemberRepositoryImpl implements MemberRepository {
	private final MemberJpaRepository memberJpaRepository;

	public MemberRepositoryImpl(MemberJpaRepository memberJpaRepository) {
		this.memberJpaRepository = memberJpaRepository;
	}

	@Override
	public Member findById(long memberId) {
		return memberJpaRepository.findById(memberId)
			.orElseThrow(() -> new CommunityException(ErrorCode.MEMBER_NOT_FOUND))
			.toDomain();
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
