package com.community.member.application.repository;

public interface MemberReader {
	boolean existsByEmail(String email);

	boolean existsByNickname(String nickname);

	boolean existsById(long memberId);
}
