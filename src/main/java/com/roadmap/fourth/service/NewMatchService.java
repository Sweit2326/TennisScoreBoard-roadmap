package com.roadmap.fourth.service;

import com.roadmap.fourth.dao.InMemoryPlayerDao;
import com.roadmap.fourth.model.MatchScore;
import com.roadmap.fourth.model.Player;

public class NewMatchService {
    private final static InMemoryPlayerDao PLAYER_DAO = new InMemoryPlayerDao();

    public MatchScore createMatch(String stPlayerName, String ndPlayerName) {
        if (isNameValid(stPlayerName) && isNameValid(ndPlayerName) && !stPlayerName.equals(ndPlayerName)) {
            Player stPlayer = validatePlayer(stPlayerName);
            Player ndPlayer = validatePlayer(ndPlayerName);
            return new MatchScore(stPlayer, ndPlayer);
        }
        return null;
    }
    private boolean isNameValid(String inputName) {
        if (inputName.length() > 100 || inputName.isEmpty()) {
            return false;
        } else
            for (char c : inputName.toCharArray()) {
                if (!Character.isLetter(c) && !Character.isSpaceChar(c)) {
                    return false;
                }
            }
        return true;
    }
    private Player validatePlayer(String inputPlayerName) {
        Player player = PLAYER_DAO.getPlayerByName(inputPlayerName);
        if (player != null) {
            return player;
        } else {
            return PLAYER_DAO.postPlayer(inputPlayerName);
        }
    }
}