package com.roadmap.fourth;

import com.roadmap.fourth.dto.MatchScoreDTO;
import com.roadmap.fourth.dto.PageDTO;
import com.roadmap.fourth.exception.BAD_REQUEST;
import com.roadmap.fourth.exception.INTERNAL_SERVER_ERROR;
import com.roadmap.fourth.service.*;
import java.util.UUID;

public class MatchScoreController {
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

    public MatchScoreDTO updateMatchScore(String uuid, String id) {
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
        return MATCH_SCORE_CALCULATION_SERVICE.processPoint(matchUUID, playerId);
    }

    public PageDTO getMatchesByPage(String pageNum) {
        final FinishedMatchesPersistenceService FINISHED_MATCHES_PERSISTENCE_SERVICE = new FinishedMatchesPersistenceService();
        int pageNumber;
        try {
            pageNumber = Math.max(1, Integer.parseInt(pageNum));
        } catch (NumberFormatException | NullPointerException e) {
            throw new BAD_REQUEST("Invalid request format");
        }

        return FINISHED_MATCHES_PERSISTENCE_SERVICE.getFinishedMatchesByPage(pageNumber);
    }
    public PageDTO getMatchesByPlayerName(String pageNum, String name) {
        final FinishedMatchesPersistenceService FINISHED_MATCHES_PERSISTENCE_SERVICE = new FinishedMatchesPersistenceService();
        int pageNumber;
        try {
            pageNumber = Math.max(1, Integer.parseInt(pageNum));
        } catch (NumberFormatException | NullPointerException e) {
            throw new BAD_REQUEST("Invalid request format:" + pageNum);
        }

        return FINISHED_MATCHES_PERSISTENCE_SERVICE.getFinishedMatchesByPlayerName(pageNumber, name);
    }
}