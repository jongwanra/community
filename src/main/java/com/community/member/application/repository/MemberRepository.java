package com.community.member.application.repository;

import java.util.Optional;

import com.community.member.domain.Member;

public interface MemberRepository {
	Optional<Member> findById(long memberId);

	Member save(Member member);

	boolean existsByNickname(String nickname);

	boolean existsByEmail(String email);

	boolean existsById(Long memberId);
}
