package com.community.member.application;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.community.global.exception.CommunityException;
import com.community.medium.MediumTestSupport;
import com.community.member.domain.Member;
import com.community.member.domain.enums.Gender;

class MemberSignUpProcessorMediumTest extends MediumTestSupport {
	private MemberSignUpProcessor memberSignUpProcessor;

	@BeforeEach
	void setUp() {
		memberSignUpProcessor = new MemberSignUpProcessor(memberRepository);

	}

	@DisplayName("회원이 DB에 제대로 적재되는지 확인한다.")
	@Test
	void save() {
		Member member = memberRepository.save(
			Member.builder()
				.gender(Gender.MALE)
				.email("uncle.ra@naver.com")
				.nickname("uncle.ra")
				.build());

		System.out.println("member = " + member);

	}

	@DisplayName("이미 가입된 이메일로 회원가입 시도 시 예외가 발생한다.")
	@Test
	void execute() {
		// given
		memberRepository.save(
			Member.builder()
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

		assertThat(savedMember.getId()).isNotNull();
		assertThat(savedMember.getGender()).isEqualTo(Gender.MALE);
		assertThat(savedMember.getEmail()).isEqualTo("uncle.ra@naver.com");
		assertThat(savedMember.getNickname()).isEqualTo("uncle.ra");

	}
}
