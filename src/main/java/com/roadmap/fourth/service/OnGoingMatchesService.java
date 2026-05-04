package com.roadmap.fourth.service;

import com.roadmap.fourth.model.MatchScore;

import java.util.HashMap;
import java.util.UUID;

public class OnGoingMatchesService {
    private HashMap<UUID, MatchScore> matches = new HashMap<>();

    public UUID createMatchUUID(String stPlayerName, String ndPlayerName) {
        NewMatchService nMS = new NewMatchService();
        MatchScore mS = nMS.createMatch(stPlayerName, ndPlayerName);

        UUID uuid = UUID.randomUUID();
        mS.setMatchUUID(uuid);
        matches.put(uuid, mS);

        return uuid;
    }
    public void setMatch(UUID uuid, MatchScore match) {
        matches.replace(uuid, match);
    }
    public MatchScore getMatch(UUID uuid) {
        return matches.get(uuid);
    }
    public void removeMatch(UUID uuid) {
        matches.remove(uuid);
    }
}
