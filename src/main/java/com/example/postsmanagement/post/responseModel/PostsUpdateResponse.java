package com.example.postsmanagement.post.responseModel;

import com.example.postsmanagement.post.dto.PostDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class PostsUpdateResponse extends PostsResponse{
    private String message;
    private List<PostDto> entities;

    public PostsUpdateResponse() {
        this.message = "Updated Successfully";
        this.entities = new ArrayList<>();
    }

    public PostsUpdateResponse(PostDto... entities) {
        this();
        Stream.of(entities).forEach(entity -> {
            this.entities.add(entity);
        });
    }

    public String getMessage() {
        return message;
    }

    public List<PostDto> getEntities() {
        return entities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostsUpdateResponse that = (PostsUpdateResponse) o;
        return Objects.equals(message, that.message) && Objects.equals(entities, that.entities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, entities);
    }
}
