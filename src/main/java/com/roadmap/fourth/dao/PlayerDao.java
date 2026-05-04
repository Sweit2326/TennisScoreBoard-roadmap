package com.roadmap.fourth.dao;

import com.roadmap.fourth.model.Player;
import org.hibernate.Session;

public interface PlayerDao {
    Player getPlayerById(int id);
    Player getPlayerByName(String name);
    Player postPlayer(String name);
}
