package com.community.post.infrastructure.entity;

import com.community.global.entity.BaseTimeEntity;
import com.community.post.domain.Post;
import com.community.post.domain.enums.PostStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostJpaEntity extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private Long id;

	private String title;
	private String content;
	private Long memberId;
	private PostStatus postStatus;

	public static PostJpaEntity from(Post post) {
		PostJpaEntity entity = new PostJpaEntity();
		entity.id = post.getId();
		entity.title = post.getTitle();
		entity.content = post.getContent();
		entity.memberId = post.getMemberId();
		entity.postStatus = post.getPostStatus();
		return entity;
	}

	public Post toDomain() {
		return Post.builder()
			.id(id)
			.title(title)
			.content(content)
			.memberId(memberId)
			.postStatus(postStatus)
			.build();
	}
}
