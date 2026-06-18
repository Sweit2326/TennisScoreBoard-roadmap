package com.roadmap.fourth.service;

import com.roadmap.fourth.dao.MatchDao;
import com.roadmap.fourth.dto.MatchScoreDTO;
import com.roadmap.fourth.dto.PageDTO;
import com.roadmap.fourth.exception.INTERNAL_SERVER_ERROR;
import com.roadmap.fourth.model.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

import static com.roadmap.fourth.util.HibernateUtil.getSessionFactory;

public class FinishedMatchesPersistenceService {
    private final static MatchDao MATCH_DAO = new MatchDao();

    public PageDTO getFinishedMatchesByPage(int pageNum) {
        Session session = getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        try {
            int totalMatches = MATCH_DAO.getMatchesCount();
            int totalPages = (totalMatches + 4) / 5;
            int reqPageNum = Math.min(totalPages, pageNum);
            int offset = (reqPageNum > 1) ? 5 * (reqPageNum - 1) : 0;
            List<Match> matches = MATCH_DAO.getMatchesOnPage(offset);

            tx.commit();
            return new PageModel(mapMatchesToMatrix(matches), reqPageNum, totalPages).buildPageDTO();
        } catch (HibernateException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            } else {
                throw new INTERNAL_SERVER_ERROR("Database error happened, try again later");
            }
        }
        return null;
    }

    public PageDTO getFinishedMatchesByPlayerName(int pageNum, String name) {
        Session session = getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        try {
            int totalMatches = MATCH_DAO.getMatchesCountByPlayerName(name);
            int totalPages = (totalMatches + 4) / 5;
            int reqPageNum = (pageNum == 1) ? 1 : Math.min(totalPages, pageNum);
            int offset = (reqPageNum > 1) ? 5 * (reqPageNum - 1) : 0;
            List<Match> matches = MATCH_DAO.getMatchesByPlayerName(offset, name);

            tx.commit();
            return new PageModel(mapMatchesToMatrix(matches), reqPageNum, totalPages).buildPageDTO();
        } catch (HibernateException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            } else {
                throw new INTERNAL_SERVER_ERROR("Database error happened, try again later");
            }
        }
        return null;
    }

    public void postFinishedMatch(MatchScoreDTO matchDTO) {
        Session session = getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            MATCH_DAO.postMatch(matchDTO);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            } else {
                throw new INTERNAL_SERVER_ERROR("Database error happened, try again later");
            }
        }
    }

    private String[][] mapMatchesToMatrix(List<Match> matches) {
        int count = 0;
        String[][] matrixMatches = new String[5][3];
        for (Match m : matches) {
            matrixMatches[count][0] = m.getPlayer1().getName();
            matrixMatches[count][1] = m.getPlayer2().getName();
            matrixMatches[count][2] = m.getWinner().getName();
            count++;
        }
        return matrixMatches;
    }
}
