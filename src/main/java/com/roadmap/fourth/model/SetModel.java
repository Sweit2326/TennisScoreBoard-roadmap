package com.roadmap.fourth.model;

public class SetModel {
    private int[] gameScore;
    private GameModel currentGame;
    private TieBreakModel currentTieBreakGame;
    private boolean isTieBreak;

    public SetModel() {
        this.gameScore = new int[]{0,0};
        this.currentGame = new GameModel();
        this.currentTieBreakGame = null;
        this.isTieBreak = false;
    }

    public boolean awardGamePointTo(int id, int opponentId) {
        boolean isGameFinished = isTieBreak ? currentTieBreakGame.awardTieBreakPointTo(id) : currentGame.awardGameScoreTo(id, opponentId);

        if ((isGameFinished && !isTieBreak && gameScore[id] == 7) || (isGameFinished && isTieBreak)) {
            return true;
        } else {
            gameScore[id]++;
            if (isGameFinished && gameScore[id] == 6 && gameScore[opponentId] == 6) {
                currentGame = null;
                currentTieBreakGame = new TieBreakModel();
            }
            return false;
        }
    }

    public int[] getCurrentGameScore() {
        return gameScore;
    }
    public String[] getCurrentGamePoints() {
        return currentGame.getGamePoints();
    }
}
