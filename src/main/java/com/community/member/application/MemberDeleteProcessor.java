package com.community.member.application;

import org.springframework.stereotype.Service;

import com.community.member.application.repository.MemberRepository;
import com.community.member.domain.Member;

@Service
public class MemberDeleteProcessor {
	private final MemberRepository memberRepository;

	public MemberDeleteProcessor(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public void execute(Command command) {
		Member member = memberRepository.findById(command.memberId)
			.orElseThrow();
		
		member.delete();
		memberRepository.save(member);
	}

	public record Command(
		long memberId
	) {

	}

}
