package com.roadmap.fourth.model;

public class TieBreakModel {
    private int[] tieBreakPoints;

    public TieBreakModel() {
        this.tieBreakPoints = new int[]{0,0};
    }

    public boolean awardTieBreakPointTo(int id) {
        return switch (tieBreakPoints[id]) {
            case 0, 1, 2, 3, 4, 5 -> {
                tieBreakPoints[id]++;
                yield false;
            }
            case 6 -> true;
            default -> false;
        };
    }

    public String[] getTieBreakPoints() {
        return new String[]{String.valueOf(tieBreakPoints[0]), String.valueOf(tieBreakPoints[1])};
    }
}
