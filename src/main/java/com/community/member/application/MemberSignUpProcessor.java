package com.community.member.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.community.global.enums.ErrorCode;
import com.community.global.exception.CommunityException;
import com.community.member.application.repository.MemberRepository;
import com.community.member.domain.Member;
import com.community.member.domain.enums.Gender;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberSignUpProcessor {
	private final MemberRepository memberRepository;

	@Transactional
	public void execute(Command command) {
		verifyEmailIsDuplicated(command.email);
		verifyNicknameIsDuplicated(command.nickname);

		memberRepository.save(
			Member
				.builder()
				.email(command.email)
				.nickname(command.nickname)
				.gender(command.gender)
				.build()
		);
	}

	private void verifyNicknameIsDuplicated(String nickname) {
		if (memberRepository.existsByNickname(nickname)) {
			throw new CommunityException(ErrorCode.MEMBER_DUPLICATED_NICKNAME);
		}
	}

	private void verifyEmailIsDuplicated(String email) {
		if (memberRepository.existsByEmail(email)) {
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
