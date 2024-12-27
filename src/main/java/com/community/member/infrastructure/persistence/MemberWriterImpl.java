package com.community.member.infrastructure.persistence;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.community.member.domain.Member;
import com.community.member.domain.repository.MemberWriter;
import com.community.member.infrastructure.entity.MemberEntity;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberWriterImpl implements MemberWriter {
	private final MemberJpaRepository memberJpaRepository;

	@Override
	public Optional<Member> findById(long memberId) {
		return memberJpaRepository.findById(memberId).map(MemberEntity::toDomain);
	}

	@Override
	public Member save(Member member) {
		return memberJpaRepository.save(MemberEntity.from(member)).toDomain();
	}
}
