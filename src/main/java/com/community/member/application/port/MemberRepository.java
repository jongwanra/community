package com.community.member.application.port;

import com.community.member.domain.Member;

public interface MemberRepository {
	Member findById(long memberId);

	Member save(Member member);

	boolean existsByNickname(String nickname);

	boolean existsByEmail(String email);

	void deleteAllInBatch();
	
}
