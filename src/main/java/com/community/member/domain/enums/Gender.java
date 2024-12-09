package com.community.member.domain.enums;

import java.util.Arrays;

import com.community.global.enums.ErrorCode;
import com.community.global.exception.CommunityException;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender {
	MALE("남성"),
	FEMALE("여성"),
	UNKNOWN("알 수 없음");

	private final String description;

	public static Gender from(String gender) {
		return Arrays.stream(Gender.values())
			.filter(g -> g.name().equalsIgnoreCase(gender))
			.findFirst()
			.orElseThrow(() -> new CommunityException(ErrorCode.NOT_SUPPORTED_GENDER, gender));
	}
}
