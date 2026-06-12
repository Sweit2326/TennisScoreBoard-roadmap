package com.roadmap.fourth.dao;

import com.roadmap.fourth.dto.MatchScoreDTO;
import com.roadmap.fourth.model.Match;
import com.roadmap.fourth.model.Player;
import org.hibernate.Session;
import java.util.List;

import static com.roadmap.fourth.util.HibernateUtil.getSessionFactory;

public class MatchDao {
    public Match getMatchById(int id) {
        Session session = getSessionFactory().getCurrentSession();
        return session.find(Match.class, id);
    }
    public List<Match> getMatchesByPlayerName(String name) {
        Session session = getSessionFactory().getCurrentSession();

        List<Match> matches = session.createQuery("FROM Match WHERE player1.name = :name OR player2.name = :name ORDER BY id", Match.class)
                .setParameter("name", name)
                .getResultList();
        return matches;
    }
    public void postMatch(MatchScoreDTO matchDTO) {
        PlayerDao plDao = new PlayerDao();
        Session session = getSessionFactory().getCurrentSession();

        Player stPlayer = plDao.getPlayerByName(matchDTO.getPlayerName(0));
        Player ndPlayer = plDao.getPlayerByName(matchDTO.getPlayerName(1));
        Player winner = (stPlayer.getName().equals(matchDTO.getWinner())) ? stPlayer : ndPlayer;

        Match match = new Match(stPlayer, ndPlayer, winner);
        session.persist(match);
    }
}
