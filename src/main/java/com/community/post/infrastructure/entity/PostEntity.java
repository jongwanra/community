package com.community.post.infrastructure.entity;

import com.community.global.entity.BaseTimeEntity;
import com.community.member.infrastructure.entity.MemberEntity;
import com.community.post.domain.Post;
import com.community.post.domain.enums.PostStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostEntity extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private Long id;

	private String title;
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false)
	private MemberEntity member;
	private PostStatus postStatus;

	public static PostEntity from(Post post) {
		PostEntity entity = new PostEntity();
		entity.id = post.getId();
		entity.title = post.getTitle();
		entity.content = post.getContent();
		entity.member = MemberEntity.from(post.getWriter());
		entity.postStatus = post.getPostStatus();
		return entity;
	}

	public Post toDomain() {
		return Post.builder()
			.id(id)
			.title(title)
			.content(content)
			.writer(member.toDomain())
			.postStatus(postStatus)
			.build();
	}
}
