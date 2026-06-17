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
        int startPage = Math.max(1, Math.min(reqPage - 1, totalPages - 2));
        int endPage = Math.min(totalPages, Math.max(reqPage + 1, 3));
        int[] pagesData = {reqPage, totalPages, startPage, endPage};

        return new PageDTO(matches, pagesData);
    }
}