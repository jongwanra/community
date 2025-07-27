package com.community.member.presentation.response;

public record MemberReadByIdResponse(
	Long memberId,
	String email
) {
}
