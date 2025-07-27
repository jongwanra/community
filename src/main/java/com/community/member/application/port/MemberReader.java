package com.community.member.application.port;

public interface MemberReader {
	boolean existsByEmail(String email);

	boolean existsByNickname(String nickname);

	boolean existsById(long memberId);
}
