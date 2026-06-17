package com.roadmap.fourth.dao;

import com.roadmap.fourth.dto.MatchScoreDTO;
import com.roadmap.fourth.model.Match;
import com.roadmap.fourth.model.Player;
import org.hibernate.Session;
import java.util.List;

import static com.roadmap.fourth.util.HibernateUtil.getSessionFactory;

public class MatchDao {
    public int getMatchesCount() {
        Session session = getSessionFactory().getCurrentSession();
        return Math.toIntExact(session.createQuery("SELECT COUNT(m) FROM Match m").getResultCount());
    }

    public int getMatchesCountByPlayerName(String name) {
        Session session = getSessionFactory().getCurrentSession();
        return Math.toIntExact(session.createQuery("SELECT COUNT(m) FROM Match m WHERE player1.name = :name OR player2.name = :name ORDER BY id").getResultCount());
    }

    public List<Match> getMatchesOnPage(int offset, int limit) {
        Session session = getSessionFactory().getCurrentSession();

        return session.createQuery("FROM Match ORDER BY id", Match.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    public List<Match> getMatchesByPlayerName(int offset, int limit, String name) {
        Session session = getSessionFactory().getCurrentSession();

        return session.createQuery("FROM Match WHERE player1.name = :name OR player2.name = :name ORDER BY id", Match.class)
                .setParameter("name", name)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
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
