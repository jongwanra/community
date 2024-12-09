package com.community.member.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.community.global.enums.ErrorCode;
import com.community.global.exception.CommunityException;
import com.community.member.domain.Member;
import com.community.member.domain.enums.Gender;
import com.community.member.domain.port.MemberReader;
import com.community.member.domain.port.MemberWriter;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberSignUpProcessor {
	private final MemberWriter memberWriter;
	private final MemberReader memberReader;

	@Transactional
	public void execute(Command command) {
		validateEmailIsDuplicated(command.email);
		validateNicknameIsDuplicated(command.nickname);

		memberWriter.save(
			Member
				.builder()
				.email(command.email)
				.nickname(command.nickname)
				.gender(command.gender)
				.build()
		);
	}

	private void validateNicknameIsDuplicated(String nickname) {
		if(memberReader.existsByNickname(nickname)) {
			throw new CommunityException(ErrorCode.MEMBER_DUPLICATED_NICKNAME);
		}
	}

	private void validateEmailIsDuplicated(String email) {
		if(memberReader.existsByEmail(email)) {
			throw new CommunityException(ErrorCode.MEMBER_DUPLICATED_EMAIL);
		}
	}

	public record Command(
		String email,
		String nickname,
		Gender gender
	) {

	}
}
