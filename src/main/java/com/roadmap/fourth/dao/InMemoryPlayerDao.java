package com.roadmap.fourth.dao;

import com.roadmap.fourth.model.Player;
import org.hibernate.Session;
import org.hibernate.Transaction;

import static com.roadmap.fourth.util.HibernateUtil.getSessionFactory;

public class InMemoryPlayerDao implements PlayerDao {
    @Override
    public Player getPlayerById(int id) {
        Session session = getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Player player = session.find(Player.class, id);
        tx.commit();
        session.close();
        return player;
    }

    @Override
    public Player getPlayerByName(String name) {
        Session session = getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Player player = session.createQuery(
                        "from Player p where p.name = :name", Player.class)
                .setParameter("name", name)
                .uniqueResult();
        tx.commit();
        session.close();
        return player;
    }

    @Override
    public Player postPlayer(String name) {
        Session session = getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Player player = new Player();
        player.setName(name);
        session.persist(player);
        tx.commit();
        session.close();

        return player;
    }
}
