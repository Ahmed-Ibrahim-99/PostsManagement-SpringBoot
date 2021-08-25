package com.example.postsmanagement.service.utils;

import com.example.postsmanagement.model.Post;
import com.example.postsmanagement.model.dto.PostDto;
import com.example.postsmanagement.repo.PostsRepository;

import java.util.ArrayList;
import java.util.List;

public class PostServiceUtils {

    public static List<String> checkDuplication(String titleEn, String titleAr, PostsRepository postsRepository) {
        List<String> duplicatedTitles = new ArrayList<>();
        if(postsRepository.existsByTitleEn(titleEn)) {
            duplicatedTitles.add("titleEn");
        }
        if(postsRepository.existsByTitleAr(titleAr)) {
            duplicatedTitles.add("titleAr");
        }
        return duplicatedTitles;
    }

    public static Post replaceNonNull(Post post, PostDto dto) {

        if(dto.getTitleEn() != null) {
            post.setTitleEn(dto.getTitleEn());
        }
        if(dto.getTitleAr() != null) {
            post.setTitleAr(dto.getTitleAr());
        }
        if(dto.getBodyEn() != null) {
            post.setBodyEn(dto.getBodyEn());
        }
        if(dto.getBodyAr() != null) {
            post.setBodyAr(dto.getBodyAr());
        }
        if(dto.getImageUrl() != null) {
            post.setImageUrl(dto.getImageUrl());
        }
        if(dto.getUrl() != null) {
            post.setUrl(dto.getUrl());
        }
        if(dto.getInterestId() != null) {
            post.setInterestId(dto.getInterestId());
        }

        return post;
    }

    public static List<String> getTitleValuesToCompare(List<String> postTitles, List<String> dtoTitles) {
        List<String> titleValuesToCompare = dtoTitles;
        if(dtoTitles.get(0) != null && dtoTitles.get(0).equals(postTitles.get(0))) {
            titleValuesToCompare.set(0, null);
        }
        if(dtoTitles.get(1) != null && dtoTitles.get(1).equals(postTitles.get(1))) {
            titleValuesToCompare.set(1, null);
        }
        return titleValuesToCompare;
    }
}
