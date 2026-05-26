package com.roadmap.fourth.service;

import com.roadmap.fourth.dao.InMemoryPlayerDao;
import com.roadmap.fourth.exception.BAD_REQUEST;
import com.roadmap.fourth.exception.INTERNAL_SERVER_ERROR;
import com.roadmap.fourth.model.MatchScore;
import com.roadmap.fourth.model.Player;

public class NewMatchService {
    private final static InMemoryPlayerDao PLAYER_DAO = new InMemoryPlayerDao();

    public MatchScore createMatch(String stPlayerName, String ndPlayerName) {
        if (isNameValid(stPlayerName.trim(), ndPlayerName.trim())) {
            Player stPlayer = validatePlayer(stPlayerName);
            Player ndPlayer = validatePlayer(ndPlayerName);
            return new MatchScore(stPlayer, ndPlayer);
        }
        throw new INTERNAL_SERVER_ERROR("Unexpected error happened while creating match!");
    }
    private boolean isNameValid(String stName, String ndName) {
        if ((stName.length() >= 50 || stName.isEmpty()) || (ndName.length() >= 50 || ndName.isEmpty())) {
            throw new BAD_REQUEST("Player name length must be 1-50 symbols!");
        }
        if (stName.equals(ndName)) {
            throw new BAD_REQUEST("Player names equals each other");
        }
        for (char c : stName.toCharArray()) {
            if (!Character.isLetter(c)) {
                throw new BAD_REQUEST("First player name contains letters or special symbols");
            }
        }
        for (char c : ndName.toCharArray()) {
            if (!Character.isLetter(c)) {
                throw new BAD_REQUEST("Second player name contains letters or special symbols");
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