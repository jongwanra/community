package com.community.member.presentation.request;

import com.community.member.application.MemberSignUpProcessor;
import com.community.member.domain.enums.Gender;

import jakarta.validation.constraints.NotBlank;

public record MemberSignUpRequest(
	@NotBlank(message = "이메일은 필수입니다.")
	String email,
	@NotBlank(message = "닉네임은 필수입니다.")
	String nickname,
	@NotBlank(message = "성별은 필수입니다.")
	String gender
) {

	public MemberSignUpProcessor.Command toCommand() {
		return new MemberSignUpProcessor.Command(
			email,
			nickname,
			Gender.from(gender)
		);
	}

}
