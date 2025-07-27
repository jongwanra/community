package com.community.small;

import org.junit.jupiter.api.BeforeEach;

import com.community.member.application.port.MemberRepository;
import com.community.mock.FakeMemberRepository;
import com.community.mock.FakePostRepository;
import com.community.post.application.port.PostRepository;

public abstract class SmallTestSupport {
	protected MemberRepository memberRepository;
	protected PostRepository postRepository;

	@BeforeEach
	void setUp() {
		memberRepository = new FakeMemberRepository();
		postRepository = new FakePostRepository();
	}
}
