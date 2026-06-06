package com.roadmap.fourth.model;

import com.roadmap.fourth.dto.MatchScoreDTO;
import lombok.Getter;
import java.util.UUID;

public class TennisMatchModel {
    @Getter
    private final UUID MATCH_UUID;
    private final String[] PLAYERS;
    private MatchScoreModel currentMatch;

    public TennisMatchModel(String stPlayer, String ndPlayer, UUID uuid) {
        this.MATCH_UUID = uuid;
        this.PLAYERS = new String[]{stPlayer, ndPlayer};
        this.currentMatch = new MatchScoreModel();
    }

    public MatchScoreDTO getMatchScoreDTO() {
        return currentMatch.buildMatchDTO();
    }

    public boolean pointWonBy(int id) {
        int opponentId = 0;
        if (id == opponentId) {
            opponentId = 1;
        }
        return currentMatch.awardPointTo(id, opponentId);
    }

    public String getPlayerName(int id) {
        return PLAYERS[id];
    }
}
