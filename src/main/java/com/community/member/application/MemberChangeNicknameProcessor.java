package com.community.member.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.community.global.enums.ErrorCode;
import com.community.global.exception.CommunityException;
import com.community.member.application.repository.MemberRepository;
import com.community.member.domain.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberChangeNicknameProcessor {
	private final MemberRepository memberRepository;

	@Transactional
	public void execute(Command command) {
		Member member = memberRepository.findById(command.memberId)
			.orElseThrow(() -> new CommunityException(ErrorCode.MEMBER_NOT_FOUND));

		if (memberRepository.existsByNickname(command.nickname)) {
			throw new CommunityException(ErrorCode.MEMBER_DUPLICATED_NICKNAME);
		}

		member.changeNickname(command.nickname);
		memberRepository.save(member);
	}

	public record Command(
		long memberId,
		String nickname
	) {

	}
}
