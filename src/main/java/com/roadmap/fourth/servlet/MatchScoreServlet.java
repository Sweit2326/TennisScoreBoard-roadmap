package com.roadmap.fourth.servlet;

import com.roadmap.fourth.MatchScoreController;
import com.roadmap.fourth.model.MatchScore;
import com.roadmap.fourth.service.OnGoingMatchesService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "MatchScoreServlet", urlPatterns = {"/match-score", "/new-match"})
public class MatchScoreServlet extends HttpServlet {
    private final MatchScoreController MATCH_SCORE_CONTROLLER = new MatchScoreController();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MatchScore currentMatch = MATCH_SCORE_CONTROLLER.getMatchByUUID(req.getParameter("uuid"));
        req.setAttribute("match", currentMatch);
        req.getRequestDispatcher("/match-score.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String servletPath = req.getServletPath();

        if (servletPath.equals("/new-match")) {
            final String stPlayerName = req.getParameter("stPl");
            final String ndPlayerName = req.getParameter("ndPl");
            MatchScore newMatch = MATCH_SCORE_CONTROLLER.createNewMatch(stPlayerName, ndPlayerName);
            resp.sendRedirect(req.getContextPath() + "/match-score?uuid=" + newMatch.getMatchUUID());
        } else if (servletPath.equals("/match-score")) {
            final int playerId = Integer.parseInt(req.getParameter("playerID"));
            final UUID matchUUID = UUID.fromString(req.getParameter("matchUUID"));
            MATCH_SCORE_CONTROLLER.updateMatchScore(matchUUID, playerId);
            resp.sendRedirect(req.getContextPath() + "/match-score?uuid=" + matchUUID);
        }
    }
}