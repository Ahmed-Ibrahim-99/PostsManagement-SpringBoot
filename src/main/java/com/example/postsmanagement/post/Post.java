package com.example.postsmanagement.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="Post", uniqueConstraints = {@UniqueConstraint(columnNames = {"postId"})})
@EnableJpaAuditing
@EntityListeners(AuditingEntityListener.class)
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
    @JsonProperty("englishTitle")
    private String titleEn;
    @NotNull
    @NotEmpty
    @Size(min=1, max=20, message="title length is out of boundary")
    @JsonProperty("arabicTitle")
    private String titleAr;
    @NotNull
    @NotEmpty
    @JsonProperty("englishParagraph")
    @Size(min=1, max=300, message="body length is out of boundary")
    private String bodyEn;
    @NotNull
    @NotEmpty
    @JsonProperty("arabicParagraph")
    @Size(min=1, max=300, message="body length is out of boundary")
    private String bodyAr;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;
    @NotNull
    @NotEmpty
    @JsonProperty("imageLink")
    private String imageUrl;
    @NotNull
    @NotEmpty
    @JsonProperty("postLink")
    private String url;
    @JsonProperty("postType")
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

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(postId, post.postId) && Objects.equals(titleEn, post.titleEn) && Objects.equals(titleAr, post.titleAr) && Objects.equals(bodyEn, post.bodyEn) && Objects.equals(bodyAr, post.bodyAr) && Objects.equals(createdAt, post.createdAt) && Objects.equals(modifiedAt, post.modifiedAt) && Objects.equals(imageUrl, post.imageUrl) && Objects.equals(url, post.url) && Objects.equals(interestId, post.interestId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, titleEn, titleAr, bodyEn, bodyAr, createdAt, modifiedAt, imageUrl, url, interestId);
    }
}
