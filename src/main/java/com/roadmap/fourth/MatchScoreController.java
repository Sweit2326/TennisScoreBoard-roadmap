package com.roadmap.fourth;

import com.roadmap.fourth.dto.MatchScoreDTO;
import com.roadmap.fourth.exception.BAD_REQUEST;
import com.roadmap.fourth.exception.INTERNAL_SERVER_ERROR;
import com.roadmap.fourth.model.Page;
import com.roadmap.fourth.service.*;
import java.util.UUID;

public class MatchScoreController {
    private final FinishedMatchesPersistenceService FINISHED_MATCHES_PERSISTENCE_SERVICE = new FinishedMatchesPersistenceService();

    public MatchScoreDTO processMatchCreation(String stPlayerName, String ndPlayerName) {
        final NewMatchService NEW_MATCH_SERVICE = new NewMatchService();
        return NEW_MATCH_SERVICE.createNewMatch(stPlayerName, ndPlayerName);
    }
    public MatchScoreDTO getMatchDTO(String uuid) {
        final OnGoingMatchesService ON_GOING_MATCHES_SERVICE = new OnGoingMatchesService();
        try {
            return ON_GOING_MATCHES_SERVICE.getMatchByUUID(UUID.fromString(uuid)).buildMatchScoreDTO();
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new BAD_REQUEST("Invalid request format");
        }
    }

    public void updateMatchScore(String uuid, String id) {
        final MatchScoreCalculationService MATCH_SCORE_CALCULATION_SERVICE = new MatchScoreCalculationService();
        final int playerId;
        final UUID matchUUID;

        try {
            playerId = Integer.parseInt(id);
        } catch (NumberFormatException | NullPointerException e) {
            throw new INTERNAL_SERVER_ERROR("Int parsing failed while updating match score");
        }
        try {
            matchUUID = UUID.fromString(uuid);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new BAD_REQUEST("Invalid request format");
        }
        MATCH_SCORE_CALCULATION_SERVICE.processPoint(matchUUID, playerId);
    }

    /* <Pagination>
    public Page getMatchesByPage(int pageNum) {
        return FINISHED_MATCHES_PERSISTENCE_SERVICE.getFinishedMatchesByPage(pageNum);
    }
    public Page getMatchesByPlayerName(int pageNum, String name) {
        return FINISHED_MATCHES_PERSISTENCE_SERVICE.getFinishedMatchesByPlayerName(pageNum, name);
    }
    </Pagination> */
}