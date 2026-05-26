package com.roadmap.fourth.util;

import com.roadmap.fourth.exception.INTERNAL_SERVER_ERROR;
import com.roadmap.fourth.model.Match;
import com.roadmap.fourth.model.Player;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
        } catch (HibernateException e) {
            throw new INTERNAL_SERVER_ERROR("Произошла ошибка во время взаимодействия с базой данных.");
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }
}

