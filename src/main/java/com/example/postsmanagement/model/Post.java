package com.example.postsmanagement.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    private String titleEn;
    private String titleAr;
    private String bodyEn;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String imageUrl;
    private String url;
    private Integer interestId;
}
