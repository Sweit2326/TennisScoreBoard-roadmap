package com.roadmap.fourth.model;

import com.roadmap.fourth.dto.MatchScoreDTO;

import java.util.UUID;

public class MatchScoreModel {
    private int[] setScore;
    private SetModel currentSet;

    public MatchScoreModel() {
        this.setScore = new int[]{0,0};
        this.currentSet = new SetModel();
    }

    public boolean awardPointTo(int id, int opponentId) {
        boolean isSetFinished = currentSet.awardGamePointTo(id, opponentId);
        if (isSetFinished && setScore[id] == 2) {
            setScore[id]++;
            return true;
        } else if (isSetFinished) {
            setScore[id]++;
            currentSet = new SetModel();
            return false;
        } else return false;
    }

    public MatchScoreDTO buildMatchDTO(String[] players, UUID uuid) {
        int winnerIndex = (setScore[0] == 3) ? 0 : (setScore[1] == 3) ? 1 : -1;

        return switch (winnerIndex) {
            case 0 -> new MatchScoreDTO(setScore, players, players[0], uuid);
            case 1 -> new MatchScoreDTO(setScore, players, players[1], uuid);
            default -> new MatchScoreDTO(setScore, currentSet.getCurrentGameScore(), currentSet.getCurrentGamePoints(), players, uuid);
        };
     }
}



