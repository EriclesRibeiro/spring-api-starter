package com.newshop.dream_shop.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class PagedResponse<T> {

    private List<T> items;
    private int page;
    private int size;
    private long totalItems;
    private int totalPages;
    private boolean lastPage;

}
