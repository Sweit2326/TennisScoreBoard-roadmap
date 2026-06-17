package com.roadmap.fourth.dto;

import lombok.Getter;

public class PageDTO {
    private String[][] matches;
    @Getter
    private int reqPage;
    @Getter
    private int totalPages;
    @Getter
    private int startPage;
    @Getter
    private int endPage;

    public PageDTO(String[][] matches, int[] pagesData) {
        this.matches = matches;
        this.reqPage = pagesData[0];
        this.totalPages = pagesData[1];
        this.startPage = pagesData[2];
        this.endPage = pagesData[3];
    }

    public String getPlayerName(int matchNum, int playerNum) {
        if (matches != null && matches.length >= matchNum) {
            return matches[matchNum][playerNum];
        } else return "";
    }
}
