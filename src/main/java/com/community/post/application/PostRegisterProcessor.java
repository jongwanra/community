package com.community.post.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.community.global.enums.ErrorCode;
import com.community.global.exception.CommunityException;
import com.community.member.domain.repository.MemberReader;
import com.community.post.domain.Post;
import com.community.post.domain.repository.PostWriter;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostRegisterProcessor {
	private final PostWriter postWriter;
	private final MemberReader memberReader;

	@Transactional
	public void execute(Command command) {
		if (!memberReader.existsById(command.memberId)) {
			throw new CommunityException(ErrorCode.MEMBER_NOT_FOUND);
		}

		// 수정 사항 발생
		Post post = Post
			.builder()
			.memberId(command.memberId())
			.title(command.title())
			.content(command.content())
			.build();

		postWriter.save(post);
	}

	public record Command(
		String title,
		String content,
		Long memberId
	) {
	}
}
