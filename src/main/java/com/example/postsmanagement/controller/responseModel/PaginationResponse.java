package com.example.postsmanagement.controller.responseModel;

import com.example.postsmanagement.model.Post;

import java.util.List;

public class PaginationResponse<T> {
    private Long entitiesNumber;
    private boolean nextPage;
    List<T> currentPageEntities;

    public PaginationResponse(Long entitiesNumber, boolean nextPage, List<T> currentPageEntities) {
        this.entitiesNumber = entitiesNumber;
        this.nextPage = nextPage;
        this.currentPageEntities = currentPageEntities;
    }

    public Long getEntitiesNumber() {
        return entitiesNumber;
    }

    public void setEntitiesNumber(Long entitiesNumber) {
        this.entitiesNumber = entitiesNumber;
    }

    public boolean isNextPage() {
        return nextPage;
    }

    public void setNextPage(boolean nextPage) {
        this.nextPage = nextPage;
    }

    public List<T> getCurrentPageEntities() {
        return currentPageEntities;
    }

    public void setCurrentPageEntities(List<T> currentPageEntities) {
        this.currentPageEntities = currentPageEntities;
    }
}
