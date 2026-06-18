package com.roadmap.fourth.servlet;

import com.roadmap.fourth.MatchScoreController;
import com.roadmap.fourth.exception.INTERNAL_SERVER_ERROR;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PaginationServlet", urlPatterns = {"/matches"})
public class PaginationServlet extends HttpServlet {
    private final MatchScoreController MATCH_SCORE_CONTROLLER = new MatchScoreController();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pageNum = req.getParameter("page");
        String playerName = req.getParameter("filter_by_player_name");

        try {
            if (playerName != null) {
                req.setAttribute("page", MATCH_SCORE_CONTROLLER.getMatchesByPlayerName(pageNum, playerName));
            } else req.setAttribute("page", MATCH_SCORE_CONTROLLER.getMatchesByPage(pageNum));
            req.getRequestDispatcher("/matches.jsp").forward(req, resp);
        } catch (ServletException e) {
            throw new INTERNAL_SERVER_ERROR("Failed to load matches page");
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final String servletPath = req.getServletPath();

        if (servletPath.equals("/matches")) {
            String pageNum = req.getParameter("page");
            String playerName = req.getParameter("filter_by_player_name");
            if (pageNum == null) {
                pageNum = "1";
            }
            if (playerName != null && !playerName.isBlank()) {
                resp.sendRedirect(req.getContextPath() + "/matches?page=" + pageNum + "&filter_by_player_name=" + playerName);
            } else {
                resp.sendRedirect(req.getContextPath() + "/matches?page=" + pageNum);
            }
        }
    }
}