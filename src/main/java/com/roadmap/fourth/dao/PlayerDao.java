package com.roadmap.fourth.dao;

import com.roadmap.fourth.model.Player;
import org.hibernate.Session;

import static com.roadmap.fourth.util.HibernateUtil.getSessionFactory;

public class PlayerDao {
    public Player getPlayerByName(String name) {
        Session session = getSessionFactory().getCurrentSession();

        return session.createQuery(
                        "FROM Player p WHERE p.name = :name", Player.class)
                .setParameter("name", name)
                .uniqueResult();
    }

    public void postPlayer(String name) {
        Session session = getSessionFactory().getCurrentSession();
        session.persist(new Player(name));
    }
}
