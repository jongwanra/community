package com.community.post.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.community.member.application.port.MemberRepository;
import com.community.member.domain.Member;
import com.community.post.application.port.PostRepository;
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
		Member member = memberRepository.findById(command.memberId);

		// 수정 사항 발생
		Post post = Post
			.builder()
			.writer(member)
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
