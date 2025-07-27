package com.community.member.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.community.member.application.port.MemberRepository;
import com.community.member.domain.Member;
import com.community.member.presentation.response.MemberReadByIdResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberReadByIdController {
	private final MemberRepository memberRepository;

	@GetMapping("/community/api/members/{memberId}")
	@ResponseStatus(HttpStatus.OK)
	public MemberReadByIdResponse readById(
		@PathVariable("memberId") Long memberId) {
		Member member = memberRepository.findById(memberId);
		return new MemberReadByIdResponse(
			member.getId(),
			member.getEmail()
		);
	}

}
