package com.community.member.mock;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.test.util.ReflectionTestUtils;

import com.community.member.domain.Member;
import com.community.member.domain.repository.MemberReader;
import com.community.member.domain.repository.MemberWriter;

public class FakeMemberRepository implements MemberReader, MemberWriter {
	final Map<Long, Member> store = new HashMap<>();
	private AtomicLong idGenerator = new AtomicLong(0);

	@Override
	public boolean existsByEmail(String email) {
		return store.values().stream()
			.anyMatch(member -> member.getEmail().equals(email));
	}

	@Override
	public boolean existsByNickname(String nickname) {
		return store.values().stream()
			.anyMatch(member -> member.getNickname().equals(nickname));
	}

	@Override
	public Optional<Member> findById(long memberId) {
		return Optional.ofNullable(store.get(memberId));
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
	public boolean existsById(long memberId) {
		return store.containsKey(memberId);
	}
}
