package com.roadmap.fourth.model;

public class TieBreakModel {
    private int[] tieBreakPoints;

    public TieBreakModel() {
        this.tieBreakPoints = new int[]{0,0};
    }

    public boolean awardTieBreakPointTo(int id) {
        return switch (tieBreakPoints[id]) {
            case 0, 1, 2, 3, 4, 5, 6 -> {
                tieBreakPoints[id]++;
                yield false;
            }
            case 7 -> true;
            default -> false;
        };
    }
}
