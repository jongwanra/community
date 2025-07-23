package com.community.post.mock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.test.util.ReflectionTestUtils;

import com.community.post.application.repository.PostRepository;
import com.community.post.domain.Post;

public class FakePostRepository implements PostRepository {
	final Map<Long, Post> store = new HashMap<>();
	private AtomicLong idGenerator = new AtomicLong(0);

	@Override
	public Post save(Post post) {
		if (post.getId() == null) {
			long postId = idGenerator.incrementAndGet();
			ReflectionTestUtils.setField(post, "id", postId);
			store.put(postId, post);
			return post;
		}

		store.put(post.getId(), post);
		return post;
	}

	@Override
	public List<Post> findAll() {
		return store.values().stream().toList();
	}
}
