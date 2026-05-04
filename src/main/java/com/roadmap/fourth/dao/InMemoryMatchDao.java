package com.roadmap.fourth.dao;

import com.roadmap.fourth.model.Match;
import com.roadmap.fourth.model.Player;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static com.roadmap.fourth.util.HibernateUtil.getSessionFactory;

public class InMemoryMatchDao implements MatchDao {
    @Override
    public Match getMatchById(int id) {
        Session session = getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Match match = session.find(Match.class, id);
        tx.commit();
        session.close();
        return match;
    }
    @Override
    public List<Match> getMatchesByPlayerName(String name) {
        Session session = getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        List<Match> matches = session.createQuery("FROM Match WHERE player1.name = :name OR player2.name = :name ORDER BY id", Match.class)
                .setParameter("name", name)
                .getResultList();
        tx.commit();
        session.close();
        return matches;
    }
    @Override
    public Match postMatch(Player stPlayer, Player ndPlayer, Player winner) {
        Session session = getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Match match = new Match();
        match.setPlayer1(stPlayer);
        match.setPlayer2(ndPlayer);
        match.setWinner(winner);
        session.persist(match);
        tx.commit();
        session.close();

        return match;
    }
}
