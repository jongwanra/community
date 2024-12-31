package com.community.member.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.community.global.annotation.ApiResponseCode;
import com.community.global.annotation.ApiResponseCodes;
import com.community.global.enums.ErrorCode;
import com.community.member.application.MemberSignUpProcessor;
import com.community.member.presentation.request.MemberSignUpRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberSignUpController {
	private final MemberSignUpProcessor memberSignUpProcessor;

	@Tag(name = "member")
	@Operation(summary = "회원가입", description = "회원가입을 합니다.")
	@ApiResponseCodes({
		@ApiResponseCode(ErrorCode.MEMBER_DUPLICATED_EMAIL),
		@ApiResponseCode(ErrorCode.MEMBER_DUPLICATED_NICKNAME)
	})
	@PostMapping("/api/members/sign-up")
	@ResponseStatus(HttpStatus.OK)
	public void signUp(
		@RequestBody MemberSignUpRequest request
	) {
		memberSignUpProcessor.execute(request.toCommand());
	}
}
