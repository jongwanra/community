package com.community.post.application;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import net.bytebuddy.utility.RandomString;

import com.community.global.exception.CommunityException;
import com.community.member.application.port.MemberRepository;
import com.community.member.domain.Member;
import com.community.member.domain.enums.Gender;
import com.community.mock.FakeMemberRepository;
import com.community.mock.FakePostRepository;
import com.community.post.application.PostRegisterProcessor.Command;
import com.community.post.application.port.PostRepository;
import com.community.post.domain.Post;
import com.community.post.domain.enums.PostStatus;

class PostRegisterProcessorTest {
	private PostRegisterProcessor postRegisterProcessor;
	private PostRepository postRepository;
	private MemberRepository memberRepository;

	@BeforeEach
	void setUp() {
		this.memberRepository = new FakeMemberRepository();
		this.postRepository = new FakePostRepository();
		postRegisterProcessor = new PostRegisterProcessor(postRepository, memberRepository);

	}

	@DisplayName("존재하지 않는 작성자일 경우 예외가 발생한다.")
	@Test
	void execute1() {
		// given
		final long notExistMemberId = 1L;
		Command command = new Command("title", "content", notExistMemberId);

		// when & then
		assertThatThrownBy(() -> {
			postRegisterProcessor.execute(command);
		})
			.isInstanceOf(CommunityException.class)
			.hasMessage("존재하지 않는 회원입니다.")
		;
	}

	@DisplayName("게시글을 등록할 수 있다.")
	@Test
	void execute2() {
		// given
		final String title = RandomString.make(10);
		final String content = RandomString.make(100);
		final long memberId = 1L;

		memberRepository.save(
			Member.builder()
				.id(memberId)
				.gender(Gender.MALE)
				.email("uncle.ra@naver.com")
				.nickname("uncle.ra")
				.build());

		Command command = new Command(title, content, memberId);

		// when
		postRegisterProcessor.execute(command);

		// then
		List<Post> posts = postRepository.findAll();
		assertThat(posts).hasSize(1);
		Post post = posts.get(0);

		assertThat(post.getTitle()).isEqualTo(title);
		assertThat(post.getContent()).isEqualTo(content);
		assertThat(post.getWriter().getId()).isEqualTo(memberId);
		assertThat(post.getPostStatus()).isEqualTo(PostStatus.PUBLISHED);
	}

}
