package com.roadmap.fourth.dao;

import com.roadmap.fourth.model.Match;
import com.roadmap.fourth.model.Player;

import java.util.List;

public interface MatchDao {
    Match getMatchById(int id);
    List<Match> getMatchesByPlayerName(String name);
    Match postMatch(Player stPlayer, Player ndPlayer, Player winner);
}
