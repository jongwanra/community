package com.community.member.domain.repository;

import java.util.Optional;

import com.community.member.domain.Member;

public interface MemberWriter {
	Optional<Member> findById(long memberId);

	Member save(Member member);
}
