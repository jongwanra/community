package com.community.member.mock;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.test.util.ReflectionTestUtils;

import com.community.member.application.repository.MemberRepository;
import com.community.member.domain.Member;

public class FakeMemberRepository implements MemberRepository {
	final Map<Long, Member> store = new HashMap<>();
	private AtomicLong idGenerator = new AtomicLong(0);

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
	public boolean existsByNickname(String nickname) {
		return false;
	}

	@Override
	public boolean existsByEmail(String email) {
		return false;
	}

	@Override
	public boolean existsById(Long memberId) {
		return false;
	}

}
