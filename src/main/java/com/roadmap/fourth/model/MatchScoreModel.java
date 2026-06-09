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
        if (setScore[0] == 3 || setScore[1] == 3) {
            return new MatchScoreDTO(setScore, players, uuid);
        } else {
            return new MatchScoreDTO(setScore, currentSet.getCurrentGameScore(), currentSet.getCurrentGamePoints(), players, uuid);
        }
     }
}



