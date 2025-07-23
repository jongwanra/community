package com.community.post.domain;

import com.community.post.domain.enums.PostStatus;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Post {
	private Long id;
	private String title;
	private String content;
	private PostStatus postStatus;
	private Long memberId;

	@Builder
	private Post(Long id, String title, String content, Long memberId, PostStatus postStatus) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.postStatus = postStatus;
		this.memberId = memberId;
	}

}
