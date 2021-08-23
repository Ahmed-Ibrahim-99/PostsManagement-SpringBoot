package com.example.postsmanagement.service;

import com.example.postsmanagement.exception.EntityAlreadyExistsException;
import com.example.postsmanagement.exception.EntityNotFoundException;
import com.example.postsmanagement.model.Post;
import com.example.postsmanagement.repo.PostsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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

    public List<Post> readAll(Integer pageNumber, Integer pageLimit) {
        Pageable paging = PageRequest.of(pageNumber, pageLimit);
        Page<Post> pagedPosts = postsRepository.findAll(paging);
        if(pagedPosts.hasContent()) {
            return pagedPosts.getContent();
        }
        else {
            return new ArrayList<Post>();
        }
    }

    public Long countAll() {
        return postsRepository.count();
    }

    public Post readById(Integer postId) {
        Post post = postsRepository.findFirstByPostId(postId);
        if(post != null) {
            return post;
        }
        throw new EntityNotFoundException(Post.class, "postId", Integer.toString(postId));
    }
}
