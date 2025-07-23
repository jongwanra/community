package com.community.post.infrastructure.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.community.post.application.repository.PostRepository;
import com.community.post.domain.Post;
import com.community.post.infrastructure.entity.PostJpaEntity;

@Repository
public class PostRepositoryImpl implements PostRepository {
	private final PostJpaRepository postJpaRepository;

	public PostRepositoryImpl(PostJpaRepository postJpaRepository) {
		this.postJpaRepository = postJpaRepository;
	}

	@Override
	public Post save(Post post) {
		return postJpaRepository.save(PostJpaEntity.from(post))
			.toDomain();
	}

	@Override
	public List<Post> findAll() {
		return postJpaRepository.findAll()
			.stream()
			.map(PostJpaEntity::toDomain)
			.toList();
	}
}
