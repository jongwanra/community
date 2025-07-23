package com.community.member.domain;

import com.community.member.domain.enums.Gender;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Member {
	private Long id;
	private String nickname;
	private String email;
	private Gender gender;

	@Builder
	private Member(Long id, String nickname, String email, Gender gender) {
		this.id = id;
		this.nickname = nickname;
		this.email = email;
		this.gender = gender;
	}

	public void changeNickname(String nickname) {
		// TODO: 특수문자 여부 체크 로직
		if (this.nickname.equals(nickname)) {
			return;
		}
		this.nickname = nickname;
	}

	public void delete() {
		// TODO ...
	}
}
