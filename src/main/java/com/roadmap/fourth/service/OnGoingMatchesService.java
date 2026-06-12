package com.roadmap.fourth.service;

import com.roadmap.fourth.dto.MatchScoreDTO;
import com.roadmap.fourth.exception.NOT_FOUND;
import com.roadmap.fourth.model.TennisMatchModel;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OnGoingMatchesService {
    private static ConcurrentHashMap<UUID, TennisMatchModel> matches = new ConcurrentHashMap<>();

    public MatchScoreDTO createActiveMatch(String stPlayerName, String ndPlayerName) {
        UUID newMatchUUID = UUID.randomUUID();
        TennisMatchModel newActiveMatch = new TennisMatchModel(stPlayerName, ndPlayerName, newMatchUUID);
        matches.put(newMatchUUID, newActiveMatch);
        return newActiveMatch.buildMatchScoreDTO();
    }
    public void updateMatchScore(UUID uuid, TennisMatchModel match) {
        matches.replace(uuid, match);
    }
    public TennisMatchModel getMatchByUUID(UUID uuid) {
        if (matches.isEmpty() || matches.get(uuid) == null) {
            throw new NOT_FOUND("Match not found, please go back to homepage");
        } else return matches.get(uuid);
    }
    public void removeActiveMatch(UUID uuid) {
        matches.remove(uuid);
    }
}
