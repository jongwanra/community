package com.community.member.domain.repository;

public interface MemberReader {
	boolean existsByEmail(String email);

	boolean existsByNickname(String nickname);
	
}
