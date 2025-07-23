package com.community.post.application.repository;

import java.util.List;

import com.community.post.domain.Post;

public interface PostRepository {
	Post save(Post post);

	List<Post> findAll();
}
