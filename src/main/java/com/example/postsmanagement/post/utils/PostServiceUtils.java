package com.example.postsmanagement.post.utils;

import com.example.postsmanagement.post.Post;
import com.example.postsmanagement.post.dto.PostDto;
import com.example.postsmanagement.post.PostsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class PostServiceUtils {

    private static final Integer TITLE_EN = 0;
    private static final Integer TITLE_AR = 1;

    public static List<String> getDuplicatedTitlesAsList(String titleEn, String titleAr, PostsRepository postsRepository) {
        List<String> duplicatedTitles = new ArrayList<>();
        if(postsRepository.existsByTitleEn(titleEn)) {
            duplicatedTitles.add("englishTitle");
        }
        if(postsRepository.existsByTitleAr(titleAr)) {
            duplicatedTitles.add("arabicTitle");
        }
        return duplicatedTitles;
    }

    public static List<String> getTitleValuesToCheckDuplicationOn(List<String> originalPostTitles, List<String> newPostTitles) {
        List<String> titleValuesToCompare = newPostTitles;
        if(newPostTitles.get(TITLE_EN) != null && newPostTitles.get(TITLE_EN).equals(originalPostTitles.get(TITLE_EN))) {
            titleValuesToCompare.set(TITLE_EN, null);
        }
        if(newPostTitles.get(TITLE_AR) != null && newPostTitles.get(TITLE_AR).equals(originalPostTitles.get(TITLE_AR))) {
            titleValuesToCompare.set(TITLE_AR, null);
        }
        return titleValuesToCompare;
    }

    public static List<String> getDuplicatedTitlesInUpdate(Post originalPost, PostDto newPost, PostsRepository postsRepository) {
        List<String> originalPostTitles = new ArrayList<String>(){{add(originalPost.getTitleEn()); add(originalPost.getTitleAr());}};
        List<String> newPostTitles = new ArrayList<String>(){{add(newPost.getEnglishTitle()); add(newPost.getArabicTitle());}};
        List<String> titlesToCheckDuplicationOn = PostServiceUtils.getTitleValuesToCheckDuplicationOn(originalPostTitles, newPostTitles);
        List<String> duplicatedTitles = PostServiceUtils.getDuplicatedTitlesAsList(titlesToCheckDuplicationOn.get(TITLE_EN), titlesToCheckDuplicationOn.get(TITLE_AR), postsRepository);
        return duplicatedTitles;
    }

    public static Post replaceNonNullNewPostFields(Post originalPost, PostDto newPost) {

        if(newPost.getEnglishTitle() != null) {
            originalPost.setTitleEn(newPost.getEnglishTitle());
        }
        if(newPost.getArabicTitle() != null) {
            originalPost.setTitleAr(newPost.getArabicTitle());
        }
        if(newPost.getEnglishParagraph() != null) {
            originalPost.setBodyEn(newPost.getEnglishParagraph());
        }
        if(newPost.getArabicParagraph() != null) {
            originalPost.setBodyAr(newPost.getArabicParagraph());
        }
        if(newPost.getImageLink() != null) {
            originalPost.setImageUrl(newPost.getImageLink());
        }
        if(newPost.getPostLink() != null) {
            originalPost.setUrl(newPost.getPostLink());
        }
        if(newPost.getPostType() != null) {
            originalPost.setInterestId(newPost.getPostType());
        }

        return originalPost;
    }


    public static List<Post> getPagePostsAsList(Page<Post> pagingResult) {
        List<Post> pagePosts;
        if(pagingResult.hasContent()) {
            pagePosts = pagingResult.getContent();
        }
        else {
            pagePosts = new ArrayList<Post>();
        }
        return pagePosts;
    }

    public static Page<Post> getPagingResult(Integer pageNumber, Integer pageLimit, PostsRepository postsRepository) {
        Pageable paging = PageRequest.of(pageNumber, pageLimit);
        Page<Post> pagingResult = postsRepository.findAll(paging);
        return pagingResult;
    }
}
