package com.community.member.infrastructure.entity;

import com.community.global.entity.BaseTimeEntity;
import com.community.member.domain.Member;
import com.community.member.domain.enums.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;

	private String nickname;

	private String email;

	private Gender gender;

	public static MemberEntity from(Member member) {
		MemberEntity memberEntity = new MemberEntity();
		memberEntity.id = member.getId();
		memberEntity.nickname = member.getNickname();
		memberEntity.email = member.getEmail();
		memberEntity.gender = member.getGender();
		return memberEntity;
	}

	public Member toDomain() {
		return Member.builder()
			.id(id)
			.nickname(nickname)
			.email(email)
			.gender(gender)
			.build();
	}
}
