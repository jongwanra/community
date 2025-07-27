package com.community.member.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.community.global.enums.ErrorCode;
import com.community.global.exception.CommunityException;
import com.community.member.application.port.MemberRepository;
import com.community.member.domain.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberChangeNicknameProcessor {
	private final MemberRepository memberRepository;

	@Transactional
	public void execute(Command command) {
		Member member = memberRepository.findById(command.memberId);

		if (memberRepository.existsByNickname(command.nickname)) {
			throw new CommunityException(ErrorCode.MEMBER_DUPLICATED_NICKNAME);
		}
		memberRepository.save(member.changeNickname(command.nickname));
	}

	public record Command(
		long memberId,
		String nickname
	) {

	}
}
