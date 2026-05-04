package com.roadmap.fourth.service;

import com.roadmap.fourth.dao.InMemoryMatchDao;
import com.roadmap.fourth.model.*;
import java.util.HashMap;
import java.util.List;

public class FinishedMatchesPersistenceService {
    private final static InMemoryMatchDao MATCH_DAO = new InMemoryMatchDao();

    public Page getFinishedMatchesByPage(int pageNum) {
        HashMap<Integer, Match> pageMatches = new HashMap<>();

        int pageNumX = (pageNum - 1) * 5 + 1;
        for (int pos = 1; pos <= 5; pos++) {
            int matchId = pageNumX + pos - 1;
            pageMatches.put(pos, MATCH_DAO.getMatchById(matchId));
        }

        if (pageMatches.get(5) != null) {
            return new Page(pageMatches, pageNum, true);
        } else return new Page(pageMatches, pageNum, false);
    }
    public Page getFinishedMatchesByPlayerName(int pageNum, String name) {
        List<Match> matches = MATCH_DAO.getMatchesByPlayerName(name);
        int fromIndex = (pageNum - 1) * 5;
        int toIndex = Math.min(fromIndex+5, matches.size());
        List<Match> subMatches = matches.subList(fromIndex, toIndex);
        HashMap<Integer, Match> pageMatches = new HashMap<>();

        for (int matchNum = 0; matchNum < subMatches.size(); matchNum++) {
            pageMatches.put(matchNum, subMatches.get(matchNum));
        }

        return new Page(pageMatches, pageNum, toIndex != matches.size());
    }
    public void postFinishedMatch(MatchScore matchScore) {
        Player stPlayer = matchScore.getPlayer(0);
        Player ndPlayer = matchScore.getPlayer(1);
        Player winner = matchScore.getWinner();
        MATCH_DAO.postMatch(stPlayer, ndPlayer, winner);
    }
}
