package com.community.member.domain;

import com.community.member.domain.enums.Gender;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Member {
	private final Long id;
	private final String nickname;
	private final String email;
	private final Gender gender;

	@Builder
	private Member(Long id, String nickname, String email, Gender gender) {
		this.id = id;
		this.nickname = nickname;
		this.email = email;
		this.gender = gender;
	}

	public Member changeNickname(String nickname) {
		// TODO: 특수문자 여부 체크 로직
		if (this.nickname.equals(nickname)) {
			return this;
		}
		return Member.builder()
			.id(this.id)
			.nickname(nickname)
			.email(this.email)
			.gender(this.gender)
			.build();
	}

	public void delete() {
		// TODO ...
	}
}
