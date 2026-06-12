package com.roadmap.fourth.dao;

import com.roadmap.fourth.model.Player;
import org.hibernate.Session;

import static com.roadmap.fourth.util.HibernateUtil.getSessionFactory;

public class PlayerDao {
//    public Player getPlayerById(int id) {
//        Session session = getSessionFactory().openSession();
//        Transaction tx = session.beginTransaction();
//
//        Player player = session.find(Player.class, id);
//        tx.commit();
//        session.close();
//        return player;
//    }

    public Player getPlayerByName(String name) {
        Session session = getSessionFactory().getCurrentSession();

        return session.createQuery(
                        "from Player p where p.name = :name", Player.class)
                .setParameter("name", name)
                .uniqueResult();
    }

    public void postPlayer(String name) {
        Session session = getSessionFactory().getCurrentSession();
        session.persist(new Player(name));
    }
}
