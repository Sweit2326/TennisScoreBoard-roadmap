package com.roadmap.fourth.service;

import com.roadmap.fourth.dao.PlayerDao;
import com.roadmap.fourth.dto.MatchScoreDTO;
import com.roadmap.fourth.exception.BAD_REQUEST;
import com.roadmap.fourth.exception.INTERNAL_SERVER_ERROR;
import com.roadmap.fourth.model.Player;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import static com.roadmap.fourth.util.HibernateUtil.getSessionFactory;

public class NewMatchService {
    private final PlayerDao PLAYER_DAO = new PlayerDao();
    private final OnGoingMatchesService ON_GOING_MATCHES_SERVICE = new OnGoingMatchesService();

    public MatchScoreDTO createNewMatch(String stPlayerName, String ndPlayerName) {
        if (isNameValid(stPlayerName.trim(), ndPlayerName.trim())) {
            validatePlayer(stPlayerName);
            validatePlayer(ndPlayerName);
            return ON_GOING_MATCHES_SERVICE.createActiveMatch(stPlayerName,ndPlayerName);
        }
        throw new INTERNAL_SERVER_ERROR("Unexpected error happened while creating match!");
    }
    private boolean isNameValid(String stName, String ndName) {
        if ((stName.length() >= 50 || stName.isEmpty()) || (ndName.length() >= 50 || ndName.isEmpty())) {
            throw new BAD_REQUEST("Player name length must be 1-50 symbols!");
        }
        if (stName.equals(ndName)) {
            throw new BAD_REQUEST("Player names equals each other");
        }
        for (char c : stName.toCharArray()) {
            if (!Character.isLetter(c)) {
                throw new BAD_REQUEST("First player name contains letters or special symbols");
            }
        }
        for (char c : ndName.toCharArray()) {
            if (!Character.isLetter(c)) {
                throw new BAD_REQUEST("Second player name contains letters or special symbols");
            }
        }
        return true;
    }
    private void validatePlayer(String inputPlayerName) {
        Session session = getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        /*
        Я тут ебался с сессиями, понял что все гуд и чет другое мешает открытию матча, но что хз.
        Выкидывает NOT_FOUND почему то. Копать надо в эту сторону.
        Также надо добавить обработку парсинга в контроллер (там помечено, поймешь)
         */

        try {
            Player player = PLAYER_DAO.getPlayerByName(inputPlayerName);
            if (player != null) {
                tx.commit();
            } else {
                PLAYER_DAO.postPlayer(inputPlayerName);
                tx.commit();
            }
        } catch (HibernateException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            } else {
                throw new INTERNAL_SERVER_ERROR("Database error happened, try again later");
            }
        }
//        finally {
//            if (session != null && session.isOpen()) {
//                session.close();
//            } else {
//                // throw new INTERNAL_SERVER_ERROR("Database error happened, try again later");
//            }
//        }
    }
}