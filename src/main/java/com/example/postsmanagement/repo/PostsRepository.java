package com.example.postsmanagement.repo;

import com.example.postsmanagement.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostsRepository extends CrudRepository<Post, Integer> {

    boolean existsByPostId(int postId);

    boolean existsByTitleEn(String titleEn);

    boolean existsByTitleAr(String TitleAr);

    void deleteByPostId(int postId);
}
