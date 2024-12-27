package com.community.member.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.community.global.enums.ErrorCode;
import com.community.global.exception.CommunityException;
import com.community.member.domain.Member;
import com.community.member.domain.repository.MemberReader;
import com.community.member.domain.repository.MemberWriter;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberChangeNicknameProcessor {
	private final MemberWriter memberWriter;
	private final MemberReader memberReader;

	@Transactional
	public void execute(Command command) {
		Member member = memberWriter.findById(command.memberId)
			.orElseThrow(() -> new CommunityException(ErrorCode.MEMBER_NOT_FOUND));

		if (memberReader.existsByNickname(command.nickname)) {
			throw new CommunityException(ErrorCode.MEMBER_DUPLICATED_NICKNAME);
		}

		member.changeNickname(command.nickname);
		memberWriter.save(member);
	}

	public record Command(
		long memberId,
		String nickname
	) {

	}
}
