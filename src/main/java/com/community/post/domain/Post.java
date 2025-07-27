package com.community.post.domain;

import com.community.member.domain.Member;
import com.community.post.domain.enums.PostStatus;

import lombok.Builder;
import lombok.Getter;

@Getter
public final class Post {
	private final Long id;
	private final String title;
	private final String content;
	private final PostStatus postStatus;
	private final Member writer;

	@Builder
	private Post(Long id, String title, String content, PostStatus postStatus, Member writer) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.postStatus = postStatus;
		this.writer = writer;
	}
}
