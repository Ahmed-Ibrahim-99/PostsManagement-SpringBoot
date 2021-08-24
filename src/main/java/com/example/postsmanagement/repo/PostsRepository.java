package com.example.postsmanagement.repo;

import com.example.postsmanagement.model.Post;
// import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostsRepository extends PagingAndSortingRepository<Post, Integer> {

    boolean existsByPostId(int postId);

    boolean existsByTitleEn(String titleEn);

    boolean existsByTitleAr(String TitleAr);

    void deleteByPostId(int postId);

    Post findFirstByPostId(int postId);

    Page<Post> findAll(Pageable pageable);

}
