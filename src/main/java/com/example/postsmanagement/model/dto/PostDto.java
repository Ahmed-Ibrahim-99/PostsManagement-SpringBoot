package com.example.postsmanagement.model.dto;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

// To be used in update request
public class PostDto {
    // Removed some members from Post:
    // postId, createdAt, modifiedAt
    private String titleEn;
    private String titleAr;
    private String bodyEn;
    private String bodyAr;
    private String imageUrl;
    private String url;
    private Integer interestId;

    public PostDto(String titleEn, String titleAr, String bodyEn, String bodyAr, String imageUrl, String url, Integer interestId) {
        this.titleEn = titleEn;
        this.titleAr = titleAr;
        this.bodyEn = bodyEn;
        this.bodyAr = bodyAr;
        this.imageUrl = imageUrl;
        this.url = url;
        this.interestId = interestId;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getTitleAr() {
        return titleAr;
    }

    public void setTitleAr(String titleAr) {
        this.titleAr = titleAr;
    }

    public String getBodyEn() {
        return bodyEn;
    }

    public void setBodyEn(String bodyEn) {
        this.bodyEn = bodyEn;
    }

    public String getBodyAr() {
        return bodyAr;
    }

    public void setBodyAr(String bodyAr) {
        this.bodyAr = bodyAr;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getInterestId() {
        return interestId;
    }

    public void setInterestId(Integer interestId) {
        this.interestId = interestId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostDto postDto = (PostDto) o;
        return Objects.equals(titleEn, postDto.titleEn) && Objects.equals(titleAr, postDto.titleAr) && Objects.equals(bodyEn, postDto.bodyEn) && Objects.equals(bodyAr, postDto.bodyAr) && Objects.equals(imageUrl, postDto.imageUrl) && Objects.equals(url, postDto.url) && Objects.equals(interestId, postDto.interestId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titleEn, titleAr, bodyEn, bodyAr, imageUrl, url, interestId);
    }
}
