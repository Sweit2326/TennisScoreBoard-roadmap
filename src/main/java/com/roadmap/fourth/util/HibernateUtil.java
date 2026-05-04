package com.roadmap.fourth.util;

import com.roadmap.fourth.model.Match;
import com.roadmap.fourth.model.Player;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.swing.*;

public class HibernateUtil {
    private static final SessionFactory SESSION_FACTORY = SessionFactoryInit();

    private static SessionFactory SessionFactoryInit() {
        try {
            SessionFactory sessionFactory = new Configuration()
                    .addAnnotatedClass(Match.class)
                    .addAnnotatedClass(Player.class)
                    .configure()
                    .buildSessionFactory();
            return sessionFactory;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }
}

