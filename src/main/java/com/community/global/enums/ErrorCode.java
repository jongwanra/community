package com.community.global.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
	// Member(ME0001 ~ ME9999)
	MEMBER_NOT_FOUND("ME0001", "존재하지 않는 회원 입니다."),
	MEMBER_DUPLICATED_EMAIL("ME0002", "이미 가입된 이메일입니다."),
	MEMBER_DUPLICATED_NICKNAME("ME0003", "이미 사용 중인 닉네임입니다."),
	NOT_SUPPORTED_GENDER("ME0004", "지원하지 않는 성별입니다. ( %s )"),
	;

	private final String errorCode;
	private final String message;

	public String formatMessage(Object... args) {
		return String.format(this.message, args);
	}
}
