package com.roadmap.fourth.dto;

import lombok.Getter;

import java.util.UUID;

public class MatchScoreDTO {
    private int[] sets;
    private int[] games;
    private String[] points;
    private String[] players;
    @Getter
    private final UUID matchUUID;

    public MatchScoreDTO(int[] sets, int[] games, String[] points, String[] players, UUID uuid) {
        this.sets = sets;
        this.games = games;
        this.points = points;
        this.players = players;
        this.matchUUID = uuid;
    }
    public MatchScoreDTO(int[] sets, String[] players, UUID uuid) {
        this.sets = sets;
        this.players = players;
        this.matchUUID = uuid;
    }

    public int getSets(int id) {
        return sets[id];
    }
    public int getGames(int id) {
        return games[id];
    }
    public String getPoints(int id) {
        return points[id];
    }
    public String getPlayerName(int id) {
        return players[id];
    }
}
