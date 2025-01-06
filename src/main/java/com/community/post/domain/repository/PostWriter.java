package com.community.post.domain.repository;

import java.util.List;

import com.community.post.domain.Post;

public interface PostWriter {
	Post save(Post post);

	List<Post> findAll();
}
