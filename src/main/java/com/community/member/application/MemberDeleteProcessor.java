package com.community.member.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.community.member.application.port.MemberRepository;
import com.community.member.domain.Member;

@Service
public class MemberDeleteProcessor {
	private final MemberRepository memberRepository;

	public MemberDeleteProcessor(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Transactional
	public void execute(Command command) {
		Member member = memberRepository.findById(command.memberId);
		member.delete();
		memberRepository.save(member);
	}

	public record Command(
		long memberId
	) {

	}

}
