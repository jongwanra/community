package com.community.post.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PostStatus {
	PUBLISHED("공개된 글"),
	HIDDEN("숨겨진 글");

	private final String description;
}
