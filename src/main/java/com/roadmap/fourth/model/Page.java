package com.roadmap.fourth.model;

import lombok.Getter;

import java.util.HashMap;

public class Page {
    private final HashMap<Integer, Match> pageMatches;
    @Getter
    private final int pageNumber;
    @Getter
    private final boolean isNextPageAvailable;

    public Page(HashMap<Integer, Match> pageMatches, int pageNum, boolean isNextPageAvailable) {
        this.pageMatches = pageMatches;
        this.pageNumber = pageNum;
        this.isNextPageAvailable = isNextPageAvailable;
    }

    public String getPlayerName(int matchNum, int playerNum) {
        Match currentMatch = pageMatches.get(matchNum);
        if (currentMatch != null) {
            return switch (playerNum) {
                case 0 -> currentMatch.getPlayer1().getName();
                case 1 -> currentMatch.getPlayer2().getName();
                default -> "";
            };
        } else {
            return "";
        }
    }
    public String getWinnerName(int matchNum) {
        Match currentMatch = pageMatches.get(matchNum);
        if (currentMatch != null) {
            return currentMatch.getWinner().getName();
        } else return "";

    }
    public int getPrevPageNumber() {
        return pageNumber-1;
    }
    public String getNextPageNumber() {
        if (isNextPageAvailable) {
            return pageNumber + 1 + "";
        } else return "";
    }
}