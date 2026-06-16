package com.roadmap.fourth.servlet;

import com.roadmap.fourth.MatchScoreController;
import com.roadmap.fourth.dto.MatchScoreDTO;
import com.roadmap.fourth.exception.BAD_REQUEST;
import com.roadmap.fourth.exception.INTERNAL_SERVER_ERROR;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "MatchScoreServlet", urlPatterns = {"/match-score", "/new-match"})
public class MatchScoreServlet extends HttpServlet {
    private final MatchScoreController MATCH_SCORE_CONTROLLER = new MatchScoreController();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        MatchScoreDTO matchDTO = MATCH_SCORE_CONTROLLER.getMatchDTO(req.getParameter("uuid"));
        try {
            req.setAttribute("match", matchDTO);
            req.getRequestDispatcher("/match-score.jsp").forward(req, resp);
        } catch (ServletException e) {
            throw new INTERNAL_SERVER_ERROR("Failed to load match page");
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String servletPath = req.getServletPath();

        if (servletPath.equals("/new-match")) {
            try {
                final String stPlayerName = req.getParameter("stPl");
                final String ndPlayerName = req.getParameter("ndPl");
                MatchScoreDTO newMatch = MATCH_SCORE_CONTROLLER.processMatchCreation(stPlayerName, ndPlayerName);
                resp.sendRedirect(req.getContextPath() + "/match-score?uuid=" + newMatch.getMatchUUID());
            } catch (BAD_REQUEST e) {
                req.setAttribute("errorMessage", e.getMessage());
                req.getRequestDispatcher("/new-match.jsp").forward(req, resp);
            }
        } else if (servletPath.equals("/match-score")) {
            final String playerId = req.getParameter("playerID");
            final String matchUUID = req.getParameter("matchUUID");
            MatchScoreDTO matchDTO = MATCH_SCORE_CONTROLLER.updateMatchScore(matchUUID, playerId);
            if (matchDTO.getWinner() != null) {
                req.setAttribute("match", matchDTO);
                req.getRequestDispatcher("/match-score.jsp").forward(req, resp);
            } else resp.sendRedirect(req.getContextPath() + "/match-score?uuid=" + matchUUID);
        }
    }
}