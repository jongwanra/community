package com.community.mock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.test.util.ReflectionTestUtils;

import com.community.global.enums.ErrorCode;
import com.community.global.exception.CommunityException;
import com.community.member.application.port.MemberRepository;
import com.community.member.domain.Member;

public class FakeMemberRepository implements MemberRepository {
	final Map<Long, Member> store = new HashMap<>();
	private AtomicLong idGenerator = new AtomicLong(0);

	@Override
	public Member findById(long memberId) {
		if (store.containsKey(memberId)) {
			return store.get(memberId);
		}
		throw new CommunityException(ErrorCode.MEMBER_NOT_FOUND);
	}

	@Override
	public Member save(Member member) {
		if (member.getId() == null) {
			long memberId = idGenerator.incrementAndGet();
			ReflectionTestUtils.setField(member, "id", memberId);
			store.put(memberId, member);
			return member;
		}

		store.put(member.getId(), member);
		return member;
	}

	@Override
	public boolean existsByNickname(String nickname) {
		return store.values().stream()
			.anyMatch(member -> member.getNickname().equals(nickname));
	}

	@Override
	public boolean existsByEmail(String email) {
		return store.values().stream()
			.anyMatch(member -> member.getEmail().equals(email));
	}

	@Override
	public boolean existsById(Long memberId) {
		return store.containsKey(memberId);
	}

}
