package com.example.postsmanagement.model.dto;

import java.util.Objects;

public class PostDto {
    private String englishTitle;
    private String arabicTitle;
    private String englishParagraph;
    private String arabicParagraph;
    private String imageLink;
    private String postLink;
    private Integer postType;

    public PostDto(String englishTitle, String arabicTitle, String englishParagraph, String arabicParagraph, String imageLink, String postLink, Integer postType) {
        this.englishTitle = englishTitle;
        this.arabicTitle = arabicTitle;
        this.englishParagraph = englishParagraph;
        this.arabicParagraph = arabicParagraph;
        this.imageLink = imageLink;
        this.postLink = postLink;
        this.postType = postType;
    }

    public String getEnglishTitle() {
        return englishTitle;
    }

    public String getArabicTitle() {
        return arabicTitle;
    }

    public String getEnglishParagraph() {
        return englishParagraph;
    }

    public String getArabicParagraph() {
        return arabicParagraph;
    }

    public String getImageLink() {
        return imageLink;
    }

    public String getPostLink() {
        return postLink;
    }

    public Integer getPostType() {
        return postType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostDto postDto = (PostDto) o;
        return Objects.equals(englishTitle, postDto.englishTitle) && Objects.equals(arabicTitle, postDto.arabicTitle) && Objects.equals(englishParagraph, postDto.englishParagraph) && Objects.equals(arabicParagraph, postDto.arabicParagraph) && Objects.equals(imageLink, postDto.imageLink) && Objects.equals(postLink, postDto.postLink) && Objects.equals(postType, postDto.postType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(englishTitle, arabicTitle, englishParagraph, arabicParagraph, imageLink, postLink, postType);
    }
}
