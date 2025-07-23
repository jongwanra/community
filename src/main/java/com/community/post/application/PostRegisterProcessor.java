package com.community.post.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.community.global.enums.ErrorCode;
import com.community.global.exception.CommunityException;
import com.community.member.application.repository.MemberRepository;
import com.community.post.application.repository.PostRepository;
import com.community.post.domain.Post;
import com.community.post.domain.enums.PostStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostRegisterProcessor {
	private final PostRepository postRepository;
	private final MemberRepository memberRepository;

	@Transactional
	public void execute(Command command) {
		if (!memberRepository.existsById(command.memberId)) {
			throw new CommunityException(ErrorCode.MEMBER_NOT_FOUND);
		}

		// 수정 사항 발생
		Post post = Post
			.builder()
			.memberId(command.memberId())
			.title(command.title())
			.content(command.content())
			.postStatus(PostStatus.PUBLISHED)
			.build();

		postRepository.save(post);
	}

	public record Command(
		String title,
		String content,
		Long memberId
	) {
	}
}
