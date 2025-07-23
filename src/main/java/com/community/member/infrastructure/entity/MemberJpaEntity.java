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
public class MemberJpaEntity extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;

	private String nickname;

	private String email;

	private Gender gender;

	public static MemberJpaEntity from(Member member) {
		MemberJpaEntity memberJpaEntity = new MemberJpaEntity();
		memberJpaEntity.id = member.getId();
		memberJpaEntity.nickname = member.getNickname();
		memberJpaEntity.email = member.getEmail();
		memberJpaEntity.gender = member.getGender();
		return memberJpaEntity;
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
