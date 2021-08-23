package com.example.postsmanagement.service;

import com.example.postsmanagement.exception.EntityAlreadyExistsException;
import com.example.postsmanagement.exception.EntityNotFoundException;
import com.example.postsmanagement.model.Post;
import com.example.postsmanagement.repo.PostsRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PostsServiceImpl implements PostsService{
    private PostsRepository postsRepository;

    public PostsServiceImpl(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    public Post create(Post post) {
        if(postsRepository.existsByTitleEn(post.getTitleEn()) &&
           postsRepository.existsByTitleAr(post.getTitleAr())) {
            throw new EntityAlreadyExistsException(Post.class, "titleEn", post.getTitleEn(), "titleAr", post.getTitleAr());
        }
        else if(postsRepository.existsByTitleEn(post.getTitleEn())) {
            throw new EntityAlreadyExistsException(Post.class, "titleEn", post.getTitleEn());
        }
        else if(postsRepository.existsByTitleAr(post.getTitleAr())) {
            throw new EntityAlreadyExistsException(Post.class, "titleAr", post.getTitleAr());
        }
        return postsRepository.save(post);
    }

    public void delete(Integer postId) {
        if(postsRepository.existsByPostId(postId)) {
            postsRepository.deleteByPostId(postId);
            return;
        }
        throw new EntityNotFoundException(Post.class, "postId", Integer.toString(postId));
    }
}
