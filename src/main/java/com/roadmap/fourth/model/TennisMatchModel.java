package com.roadmap.fourth.model;

import com.roadmap.fourth.dto.MatchScoreDTO;
import lombok.Getter;
import java.util.UUID;

public class TennisMatchModel {
    @Getter
    private final UUID MATCH_UUID;
    private final String[] PLAYERS;
    private MatchScoreModel matchScore;

    public TennisMatchModel(String stPlayer, String ndPlayer, UUID uuid) {
        this.MATCH_UUID = uuid;
        this.PLAYERS = new String[]{stPlayer, ndPlayer};
        this.matchScore = new MatchScoreModel();
    }

    public MatchScoreDTO buildMatchScoreDTO() {
        return matchScore.buildMatchDTO(PLAYERS, MATCH_UUID);
    }

    public boolean pointWonBy(int id) {
        int opponentId = 0;
        if (id == opponentId) {
            opponentId = 1;
        }
        return matchScore.awardPointTo(id, opponentId);
    }

    public String getPlayerName(int id) {
        return PLAYERS[id];
    }
}
