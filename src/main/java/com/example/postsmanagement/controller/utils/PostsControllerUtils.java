package com.example.postsmanagement.controller.utils;

import java.util.ArrayList;
import java.util.List;

public class PostsControllerUtils {
    public static List<String> checkRequestParams(int pageNumber, int pageLimit) {
        List<String> invalidParams = new ArrayList<>();
        if(pageNumber < 0) {
            invalidParams.add("pageNumber");
        }
        if(pageLimit < 1) {
            invalidParams.add("pageLimit");
        }
        return invalidParams;
    }
}
