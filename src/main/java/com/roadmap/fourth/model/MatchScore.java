package com.roadmap.fourth.model;

import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

public class MatchScore {
    int[] sets = new int[2];
    int[] games = new int[2];
    int[] points = new int[2];
    Player[] players = new Player[2];
    @Getter
    Player winner = null;
    @Setter
    @Getter
    boolean isMatchFinished = false;
    @Setter
    @Getter
    boolean isTieBreak = false;
    @Setter
    @Getter
    UUID matchUUID;

    public MatchScore(Player stPlayer, Player ndPlayer) {
        this.players[0] = stPlayer;
        this.players[1] = ndPlayer;
    }

    public String getPlayerName(int id) {
        return players[id].getName();
    }

    public Player getPlayer(int id) {
        return players[id];
    }

    public int getPoints(int id) {
        return points[id];
    }

    public String getPointsValue(int id) {
        String pointsValue = String.valueOf(points[id]);
        if (pointsValue.equals("41")) {
            return "AD";
        } else return pointsValue;
    }

    public int getGames(int id) {
        return games[id];
    }

    public int getSets(int id) {
        return sets[id];
    }

    public void setPoints(int id, int amount) {
        points[id] = amount;
    }

    public void setGames(int id, int amount) {
        games[id] = amount;
    }

    public void setSets(int id, int amount) {
        sets[id] = amount;
    }

    public void setWinner(int id) {
        winner = players[id];
    }
}