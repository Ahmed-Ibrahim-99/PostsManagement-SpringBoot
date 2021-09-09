package com.example.postsmanagement.post.responseModel;

import java.util.List;

public class PostsPaginationResponse<T> extends PostsResponse{
    private Long entitiesNumber;
    private boolean nextPage;
    List<T> currentPageEntities;

    public PostsPaginationResponse(Long entitiesNumber, boolean nextPage, List<T> currentPageEntities) {
        this.entitiesNumber = entitiesNumber;
        this.nextPage = nextPage;
        this.currentPageEntities = currentPageEntities;
    }

    public Long getEntitiesNumber() {
        return entitiesNumber;
    }

    public boolean isNextPage() {
        return nextPage;
    }

    public List<T> getCurrentPageEntities() {
        return currentPageEntities;
    }
}
