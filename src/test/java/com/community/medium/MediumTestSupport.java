package com.community.medium;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import com.community.member.application.port.MemberRepository;
import com.community.post.application.port.PostRepository;

@MediumTest
public abstract class MediumTestSupport {
	@Autowired
	protected MemberRepository memberRepository;
	@Autowired
	protected PostRepository postRepository;

	@BeforeEach
	void setUp() {
		memberRepository.deleteAllInBatch();
		postRepository.deleteAllInBatch();
	}
}
