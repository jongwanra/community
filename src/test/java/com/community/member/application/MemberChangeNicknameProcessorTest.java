package com.community.member.application;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.community.global.exception.CommunityException;
import com.community.member.application.repository.MemberRepository;
import com.community.member.domain.Member;
import com.community.member.domain.enums.Gender;
import com.community.member.mock.FakeMemberRepository;

class MemberChangeNicknameProcessorTest {
	private MemberChangeNicknameProcessor memberChangeNicknameProcessor;
	private MemberRepository memberRepository;

	@BeforeEach
	void setUp() {
		this.memberRepository = new FakeMemberRepository();
		memberChangeNicknameProcessor = new MemberChangeNicknameProcessor(memberRepository);
	}

	@DisplayName("회원은 닉네임을 변경할 수 있다.")
	@Test
	void execute1() {
		// given
		long memberId = 1L;
		final String nickname = "uncle.ra";
		final String nicknameToChange = "changed.ra";

		Member member = Member.builder()
			.id(memberId)
			.nickname(nickname)
			.email("uncle.ra@gmail.com")
			.gender(Gender.MALE)
			.build();

		memberRepository.save(member);

		// when
		memberChangeNicknameProcessor.execute(new MemberChangeNicknameProcessor.Command(
			memberId,
			nicknameToChange
		));

		Member changedMember = memberRepository.findById(memberId).orElseThrow();

		// then
		assertThat(changedMember.getNickname()).isEqualTo(nicknameToChange);
		assertThat(changedMember.getId()).isEqualTo(memberId);
		assertThat(changedMember.getGender()).isEqualTo(Gender.MALE);

	}

	@DisplayName("변경할 닉네임을 가진 다른 회원이 이미 존재한 경우 예외를 발생시킨다.")
	@Test
	void execute2() {
		// given
		long memberId = 1L;
		final String nickname = "uncle.ra";
		final String sameNickname = "uncle.ra";

		Member memberHasSameNickname = Member.builder()
			.id(memberId)
			.nickname(nickname)
			.email("uncle.ra@gmail.com")
			.gender(Gender.MALE)
			.build();

		memberRepository.save(memberHasSameNickname);

		// when & then
		assertThatThrownBy(() -> {
			memberChangeNicknameProcessor.execute(new MemberChangeNicknameProcessor.Command(
				memberId,
				sameNickname
			));
		})
			.isInstanceOf(CommunityException.class)
			.hasMessage("이미 사용 중인 닉네임입니다.");
	}

	@DisplayName("특수문자가 존재하는 닉네임인 경우 예외를 발생시킨다.")
	@Test
	void execute3() {
		// given
		// when
		// then
	}

}
