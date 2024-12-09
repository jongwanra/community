package com.community.member.domain.port;

public interface MemberReader {
	boolean existsByEmail(String email);

	boolean existsByNickname(String nickname);
}
