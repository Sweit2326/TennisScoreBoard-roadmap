package com.roadmap.fourth;

import com.roadmap.fourth.model.MatchScore;
import com.roadmap.fourth.model.Page;
import com.roadmap.fourth.service.*;
import java.util.UUID;

public class MatchScoreController {
    private final OnGoingMatchesService ON_GOING_MATCHES_SERVICE = new OnGoingMatchesService();
    private final FinishedMatchesPersistenceService FINISHED_MATCHES_PERSISTENCE_SERVICE = new FinishedMatchesPersistenceService();

    public MatchScore createNewMatch(String stPlayerName, String ndPlayerName) {
        UUID matchUUID = ON_GOING_MATCHES_SERVICE.createMatchUUID(stPlayerName, ndPlayerName);
        return ON_GOING_MATCHES_SERVICE.getMatch(matchUUID);
    }
    public void updateMatchScore(UUID matchUUID, int playerId) {
        MatchScoreCalculationService mSCS = new MatchScoreCalculationService();
        MatchScore currentMatch = ON_GOING_MATCHES_SERVICE.getMatch(matchUUID);
        MatchScore updatedMatch = mSCS.pointWonBy(currentMatch, playerId);
        ON_GOING_MATCHES_SERVICE.setMatch(matchUUID, updatedMatch);
    }
    public MatchScore getMatchByUUID(String uuid) {
        MatchScore match = ON_GOING_MATCHES_SERVICE.getMatch(UUID.fromString(uuid));
        if (match.isMatchFinished()) {
            ON_GOING_MATCHES_SERVICE.removeMatch(match.getMatchUUID());
            FINISHED_MATCHES_PERSISTENCE_SERVICE.postFinishedMatch(match);
        }
        return match;
    }
    public Page getMatchesByPage(int pageNum) {
        return FINISHED_MATCHES_PERSISTENCE_SERVICE.getFinishedMatchesByPage(pageNum);
    }
    public Page getMatchesByPlayerName(int pageNum, String name) {
        return FINISHED_MATCHES_PERSISTENCE_SERVICE.getFinishedMatchesByPlayerName(pageNum, name);
    }
}