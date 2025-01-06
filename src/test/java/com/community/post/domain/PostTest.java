package com.community.post.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import net.bytebuddy.utility.RandomString;

import com.community.post.domain.enums.PostStatus;

class PostTest {

	@DisplayName("게시글을 생성할 수 있다.")
	@Test
	void constructor() {
		// given
		final Long postId = 3L;
		final String content = RandomString.make(1_000);
		final Long memberId = 2L;
		final String title = RandomString.make(100);

		// when
		Post post = Post.builder()
			.id(postId)
			.title(title)
			.memberId(memberId)
			.content(content)
			.build();

		// then
		assertThat(post.getId()).isEqualTo(postId);
		assertThat(post.getTitle()).isEqualTo(title);
		assertThat(post.getMemberId()).isEqualTo(memberId);
		assertThat(post.getContent()).isEqualTo(content);
		assertThat(post.getPostStatus()).isEqualTo(PostStatus.PUBLISHED);
	}

	@DisplayName("게시글의 제목은 최대 100자까지 가능하다.")
	@Test
	void constructor1() {
		// given
		// when
		// then
	}

	@DisplayName("게시글의 내용은 최대 1,000자까지 가능하다.")
	@Test
	void constructor2() {
		// given
		// when
		// then
	}

}
