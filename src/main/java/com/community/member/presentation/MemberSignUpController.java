package com.community.member.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.community.member.application.MemberSignUpProcessor;
import com.community.member.presentation.request.MemberSignUpRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberSignUpController {
	private final MemberSignUpProcessor memberSignUpProcessor;

	@PostMapping("/api/members/sign-up")
	@ResponseStatus(HttpStatus.OK)
	public void signUp(
		@RequestBody MemberSignUpRequest request
	) {
		memberSignUpProcessor.execute(request.toCommand());
	}
}
