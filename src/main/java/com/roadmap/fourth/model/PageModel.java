package com.roadmap.fourth.model;

import com.roadmap.fourth.dto.PageDTO;
import java.util.List;

public class PageModel {
    private final String[][] matches;
    private final int reqPage;
    private final int totalPages;

    public PageModel(String[][] matches, int reqPage, int totalPages) {
        this.matches = matches;
        this.reqPage = reqPage;
        this.totalPages = totalPages;
    }

    public PageDTO buildPageDTO() {
        int startPage = Math.max(1, reqPage - 1);
        int endPage = Math.min(totalPages, reqPage + 1);
        int[] pagesData = {reqPage, totalPages, startPage, endPage};

        return new PageDTO(matches, pagesData);
    }
}