package com.community.member.infrastructure.persistence;

import static com.community.member.infrastructure.entity.QMemberEntity.*;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.community.global.enums.ErrorCode;
import com.community.global.exception.CommunityException;
import com.community.member.application.port.MemberRepository;
import com.community.member.domain.Member;
import com.community.member.infrastructure.entity.MemberEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class MemberRepositoryImpl implements MemberRepository {
	private static final String CACHE_KEY_PREFIX = "member:";
	private final MemberJpaRepository memberJpaRepository;
	private final JPAQueryFactory queryFactory;
	private final RedisTemplate<String, String> redisTemplate;
	private final ObjectMapper objectMapper;

	public MemberRepositoryImpl(MemberJpaRepository memberJpaRepository, RedisTemplate<String, String> redisTemplate,
		EntityManager entityManager) {
		this.memberJpaRepository = memberJpaRepository;
		this.queryFactory = new JPAQueryFactory(entityManager);
		this.redisTemplate = redisTemplate;
		this.objectMapper = new ObjectMapper();

	}

	@Override
	public Member findById(long memberId) {
		try {
			final String cacheKey = CACHE_KEY_PREFIX + memberId;
			// 1.  캐시에서 먼저 조회
			final String serialized = redisTemplate.opsForValue().get(cacheKey);
			log.info("serialized = {}", serialized);

			if (serialized != null) {
				log.info("CACHE HIT - Returning member from cache");
				return objectMapper.readValue(serialized, MemberEntity.class)
					.toDomain();
			}
			// 2. 캐시에 없으면 DB에서 조회
			log.info("CACHE MISS - Fetching member from database");

			MemberEntity memberEntity = memberJpaRepository.findById(memberId)
				.orElseThrow(() -> new CommunityException(ErrorCode.MEMBER_NOT_FOUND));

			redisTemplate.opsForValue().set(cacheKey, objectMapper.writeValueAsString(memberEntity));
			return memberEntity.toDomain();
		} catch (JsonProcessingException e) {
			throw new CommunityException(e);
		}
	}

	@Override
	public Member save(Member member) {
		MemberEntity memberEntity = MemberEntity.from(member);
		return memberJpaRepository.save(memberEntity)
			.toDomain();
	}

	@Override
	public boolean existsByNickname(String nickname) {
		return queryFactory
			.select(memberEntity.id)
			.from(memberEntity)
			.where(memberEntity.nickname.eq(nickname))
			.fetchFirst() != null;
	}

	@Override
	public boolean existsByEmail(String email) {
		return queryFactory
			.select(memberEntity.id)
			.from(memberEntity)
			.where(memberEntity.email.eq(email))
			.fetchFirst() != null;
	}

	@Override
	public void deleteAllInBatch() {
		memberJpaRepository.deleteAllInBatch();
		// 캐시도 비워야 함
		redisTemplate.keys(CACHE_KEY_PREFIX + "*")
			.forEach(redisTemplate::delete);
		log.info("All members deleted from database and cache cleared.");
	}

}
