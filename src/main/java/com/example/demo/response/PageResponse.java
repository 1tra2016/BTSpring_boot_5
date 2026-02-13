package com.example.demo.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageResponse<T> {

    private List<T> items;
    private int page;
    private int size;
    private long totalItems;
    private int totalPages;
    private boolean isLast;

    public PageResponse(List<T> items,
                        int page,
                        int size,
                        long totalItems,
                        int totalPages,
                        boolean isLast) {
        this.items = items;
        this.page = page;
        this.size = size;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.isLast = isLast;
    }
}
