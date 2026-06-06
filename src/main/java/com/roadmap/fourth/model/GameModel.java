package com.roadmap.fourth.model;

import lombok.Getter;

public class GameModel {
    private PointScore[] points;

    public GameModel() {
        this.points = new PointScore[]{PointScore.ZERO, PointScore.ZERO};
    }

    public String[] getGamePoints() {
        return new String[]{points[0].getPointsTitle(), points[1].getPointsTitle()};
    }

    public boolean awardGameScoreTo(int id, int opponentId) {
        switch (points[id]) {
            case ZERO: points[id] = PointScore.FIFTEEN;
            case FIFTEEN: points[id] = PointScore.THIRTY;
            case THIRTY: points[id] = PointScore.FOURTY;
            case FOURTY, ADVANTAGE:
                if (points[opponentId] == PointScore.FOURTY) {
                    return handleDeuceScore(id, opponentId);
                } else return true;
            default:
                return false;
        }
    }

    private boolean handleDeuceScore(int id, int opponentId) {
        if (points[id] == PointScore.FOURTY && points[opponentId] == PointScore.FOURTY) {
            points[id] = PointScore.ADVANTAGE;
            return false;
        } else if (points[id] == PointScore.ADVANTAGE && points[opponentId] == PointScore.FOURTY) {
            return true;
        } else {
            points[id] = PointScore.FOURTY;
            points[opponentId] = PointScore.FOURTY;
            return false;
        }
    }

    private enum PointScore {
        ZERO("0"),
        FIFTEEN("15"),
        THIRTY("30"),
        FOURTY("40"),
        ADVANTAGE("AD");

        @Getter
        private final String pointsTitle;

        PointScore(String points) {
            this.pointsTitle = points;
        }
    }
}
