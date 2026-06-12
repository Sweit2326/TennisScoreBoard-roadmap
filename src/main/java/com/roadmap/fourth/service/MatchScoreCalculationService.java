package com.roadmap.fourth.service;

import com.roadmap.fourth.dto.MatchScoreDTO;
import com.roadmap.fourth.model.TennisMatchModel;

import java.util.UUID;

public class MatchScoreCalculationService {
    public MatchScoreDTO processPoint(UUID uuid, int playerId) {
        final OnGoingMatchesService ON_GOING_MATCHES_SERVICE = new OnGoingMatchesService();
        final FinishedMatchesPersistenceService FINISHED_MATCHES_PERSISTENCE_SERVICE = new FinishedMatchesPersistenceService();
        TennisMatchModel match = ON_GOING_MATCHES_SERVICE.getMatchByUUID(uuid);

        if (match.pointWonBy(playerId)) {
            MatchScoreDTO matchDTO = match.buildMatchScoreDTO();
            ON_GOING_MATCHES_SERVICE.removeActiveMatch(uuid);
            FINISHED_MATCHES_PERSISTENCE_SERVICE.postFinishedMatch(matchDTO);
            return matchDTO;
        } else {
            ON_GOING_MATCHES_SERVICE.updateMatchScore(uuid, match);
            return match.buildMatchScoreDTO();
        }
    }
}
