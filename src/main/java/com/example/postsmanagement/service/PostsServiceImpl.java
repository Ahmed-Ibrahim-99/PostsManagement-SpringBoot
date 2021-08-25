package com.example.postsmanagement.service;

import com.example.postsmanagement.controller.responseModel.PaginationResponse;
import com.example.postsmanagement.exception.EntityAlreadyExistsException;
import com.example.postsmanagement.exception.EntityNotFoundException;
import com.example.postsmanagement.model.Post;
import com.example.postsmanagement.model.dto.PostDto;
import com.example.postsmanagement.repo.PostsRepository;
import com.example.postsmanagement.service.utils.PostServiceUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
/*
    This service ensures no two Posts have the same title(En/Ar)
 */
@Service
@Transactional
public class PostsServiceImpl implements PostsService {
    private PostsRepository postsRepository;

    public PostsServiceImpl(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    public Post addNewPost(Post post) {
        List<String> duplicatedTitles = PostServiceUtils.checkDuplication(post.getTitleEn(),post.getTitleAr(), postsRepository);
        if(!duplicatedTitles.isEmpty()) {
            throw new EntityAlreadyExistsException(duplicatedTitles);
        }
        return postsRepository.save(post);
    }

    public void deletePost(Integer postId) {
        if(!postsRepository.existsByPostId(postId)) {
            throw new EntityNotFoundException();
        }
        postsRepository.deleteByPostId(postId);
    }

    public PaginationResponse<Post> getPage(Integer pageNumber, Integer pageLimit) {
        Pageable paging = PageRequest.of(pageNumber, pageLimit);
        Page<Post> pagedPosts = postsRepository.findAll(paging);
        List<Post> posts;
        if(pagedPosts.hasContent()) {
            posts = pagedPosts.getContent();
        }
        else {
            posts = new ArrayList<Post>();
        }
        boolean nextPage = pagedPosts.getTotalElements()/ pageLimit - 1 > pageNumber;
        return new PaginationResponse<Post>(pagedPosts.getTotalElements(),nextPage,posts);
    }

    public Post getPost(Integer postId) {
        Post post = postsRepository.findFirstByPostId(postId);
        if (post == null) {
            throw new EntityNotFoundException();
        }
        return post;
    }

    public void updatePost(Integer postId, PostDto dto) {
        Post post = postsRepository.findFirstByPostId(postId);

        if(post == null) {
            throw new EntityNotFoundException();
        }

        List<String> postTitles = new ArrayList<String>(){{add(post.getTitleEn()); add(post.getTitleAr());}};
        List<String> dtoTitles = new ArrayList<String>(){{add(dto.getTitleEn()); add(dto.getTitleAr());}};
        List<String> titlesToCompare = PostServiceUtils.getTitleValuesToCompare(postTitles, dtoTitles);
        List<String> duplicatedTitles = PostServiceUtils.checkDuplication(titlesToCompare.get(0), titlesToCompare.get(1), postsRepository);

        if(!duplicatedTitles.isEmpty()) {
            throw new EntityAlreadyExistsException(duplicatedTitles);
        }

        Post updatedPost = PostServiceUtils.replaceNonNull(post, dto);

        postsRepository.save(updatedPost);
    }
}
