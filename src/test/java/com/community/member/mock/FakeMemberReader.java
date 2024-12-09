package com.community.member.mock;

import com.community.member.domain.port.MemberReader;

public class FakeMemberReader implements MemberReader {
	private final FakeMemberWriter fakeMemberWriter;

	public FakeMemberReader(FakeMemberWriter fakeMemberWriter) {
		this.fakeMemberWriter = fakeMemberWriter;
	}

	@Override
	public boolean existsByEmail(String email) {
		return fakeMemberWriter.store.values().stream()
			.anyMatch(member -> member.getEmail().equals(email));
	}

	@Override
	public boolean existsByNickname(String nickname) {
		return fakeMemberWriter.store.values().stream()
			.anyMatch(member -> member.getNickname().equals(nickname));
	}
}
