package com.community.member.application;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.community.global.exception.CommunityException;
import com.community.member.application.port.MemberRepository;
import com.community.member.domain.Member;
import com.community.member.domain.enums.Gender;
import com.community.mock.FakeMemberRepository;

class MemberSignUpProcessorTest {
	private MemberSignUpProcessor memberSignUpProcessor;
	private MemberRepository memberRepository;

	@BeforeEach
	void setUp() {
		this.memberRepository = new FakeMemberRepository();
		memberSignUpProcessor = new MemberSignUpProcessor(memberRepository);

	}

	@DisplayName("이미 가입된 이메일로 회원가입 시도 시 예외가 발생한다.")
	@Test
	void execute() {
		// given
		memberRepository.save(
			Member.builder()
				.id(1L)
				.gender(Gender.MALE)
				.email("uncle.ra@naver.com")
				.nickname("uncle.ra")
				.build());

		MemberSignUpProcessor.Command command = new MemberSignUpProcessor.Command(
			"uncle.ra@naver.com",
			"sera.kim",
			Gender.FEMALE
		);
		// when & then
		assertThatThrownBy(() -> memberSignUpProcessor.execute(command))
			.isInstanceOf(CommunityException.class)
			.hasMessage("이미 가입된 이메일입니다.");
	}

	@DisplayName("이미 가입된 닉네임으로 회원가입 시도 시 예외가 발생한다.")
	@Test
	void execute2() {
		// given
		memberRepository.save(
			Member.builder()
				.id(1L)
				.gender(Gender.MALE)
				.email("uncle.ra@naver.com")
				.nickname("uncle.ra")
				.build());

		MemberSignUpProcessor.Command command = new MemberSignUpProcessor.Command(
			"sera.kim@naver.com",
			"uncle.ra",
			Gender.FEMALE
		);
		// when & then
		assertThatThrownBy(() -> memberSignUpProcessor.execute(command))
			.isInstanceOf(CommunityException.class)
			.hasMessage("이미 사용 중인 닉네임입니다.");
	}

	@DisplayName("정상적으로 회원가입 처리 된다.")
	@Test
	void execute3() {

		Member savedMember = memberRepository.save(Member.builder()
			.gender(Gender.MALE)
			.email("uncle.ra@naver.com")
			.nickname("uncle.ra")
			.build()
		);

		assertThat(savedMember.getId()).isEqualTo(1L);
		assertThat(savedMember.getGender()).isEqualTo(Gender.MALE);
		assertThat(savedMember.getEmail()).isEqualTo("uncle.ra@naver.com");
		assertThat(savedMember.getNickname()).isEqualTo("uncle.ra");

	}
}
