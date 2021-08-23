package com.example.postsmanagement.repo;

import com.example.postsmanagement.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostsRepository extends CrudRepository<Post, Integer> {

}
