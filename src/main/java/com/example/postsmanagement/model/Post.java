package com.example.postsmanagement.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name="Post", uniqueConstraints = {@UniqueConstraint(columnNames = {"postId"})})
@SecondaryTables({
        @SecondaryTable(name="Interest")
})
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    @NotNull
    @NotEmpty
    @Size(min=1, max=20, message="title length is out of boundary")
    private String titleEn;
    @NotNull
    @NotEmpty
    @Size(min=1, max=20, message="title length is out of boundary")
    private String titleAr;
    @NotNull
    @NotEmpty
    @Size(min=1, max=20, message="body length is out of boundary")
    private String bodyEn;
    @NotNull
    @NotEmpty
    @Size(min=1, max=20, message="body length is out of boundary")
    private String bodyAr;
    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant modifiedAt;
    @NotNull
    @NotEmpty
    private String imageUrl;
    @NotNull
    @NotEmpty
    private String url;
    private Integer interestId;

    public void setPostId(Integer id) {
        this.postId = postId;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public void setTitleAr(String titleAr) {
        this.titleAr = titleAr;
    }

    public void setBodyEn(String bodyEn) {
        this.bodyEn = bodyEn;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setModifiedAt(Instant modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setInterestId(Integer interestId) {
        this.interestId = interestId;
    }

    public void setBodyAr(String bodyAr) {
        this.bodyAr = bodyAr;
    }

    public Integer getPostId() {
        return postId;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public String getTitleAr() {
        return titleAr;
    }

    public String getBodyAr() {
        return bodyAr;
    }

    public String getBodyEn() {
        return bodyEn;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getModifiedAt() {
        return modifiedAt;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getUrl() {
        return url;
    }

    public Integer getInterestId() {
        return interestId;
    }
}
