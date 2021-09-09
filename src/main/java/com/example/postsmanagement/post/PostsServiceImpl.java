package com.example.postsmanagement.post;

import com.example.postsmanagement.post.responseModel.PostsPaginationResponse;
import com.example.postsmanagement.exception.EntityAlreadyExistsException;
import com.example.postsmanagement.exception.EntityNotFoundException;
import com.example.postsmanagement.post.dto.PostDto;
import com.example.postsmanagement.post.utils.PostServiceUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public Post addNewPost(Post newPost) {
        List<String> duplicatedTitles = PostServiceUtils.getDuplicatedTitlesAsList(newPost.getTitleEn(),newPost.getTitleAr(), postsRepository);
        if(!duplicatedTitles.isEmpty()) {throw new EntityAlreadyExistsException(duplicatedTitles);}
        return postsRepository.save(newPost);
    }

    public void deletePost(Integer postId) {
        if(!postsRepository.existsByPostId(postId)) {throw new EntityNotFoundException();}
        postsRepository.deleteByPostId(postId);
    }

    public PostsPaginationResponse<Post> getPage(Integer pageNumber, Integer pageLimit) {
        Integer currentPageNumber = pageNumber > 0 ? pageNumber-1 : 0;
        Integer currentPageLimit = pageNumber > 0 ? pageLimit : Integer.MAX_VALUE;
        Page<Post> pagingResult = PostServiceUtils.getPagingResult(currentPageNumber, currentPageLimit, postsRepository);
        List<Post> pagePosts = PostServiceUtils.getPagePostsAsList(pagingResult);
        boolean isNextPage = pagingResult.getTotalElements()/ currentPageLimit - 1 > currentPageNumber;
        return new PostsPaginationResponse<Post>(pagingResult.getTotalElements(),isNextPage,pagePosts);
    }

    public Post getPost(Integer postId) {
        Post searchedPost = postsRepository.findFirstByPostId(postId);
        if (searchedPost == null) {throw new EntityNotFoundException();}
        return searchedPost;
    }

    public void updatePost(Integer postId, PostDto newPost) {
        Post originalPost = postsRepository.findFirstByPostId(postId);
        if(originalPost == null) {throw new EntityNotFoundException();}
        List<String> duplicatedTitles = PostServiceUtils.getDuplicatedTitlesInUpdate(originalPost, newPost, postsRepository);
        if(!duplicatedTitles.isEmpty()) {throw new EntityAlreadyExistsException(duplicatedTitles);}
        Post updatedPost = PostServiceUtils.replaceNonNullNewPostFields(originalPost, newPost);
        postsRepository.save(updatedPost);
    }
}
