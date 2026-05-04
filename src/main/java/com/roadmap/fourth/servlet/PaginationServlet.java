package com.roadmap.fourth.servlet;

import com.roadmap.fourth.MatchScoreController;
import com.roadmap.fourth.model.Page;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageNumParam = req.getParameter("page");
        String playerName = req.getParameter("filter_by_player_name");
        Page currentPage;
        int pageNum = 1;

        if (pageNumParam != null && !pageNumParam.isEmpty()) {
            pageNum = Integer.parseInt(pageNumParam);
        }
        if (playerName != null && !playerName.trim().isEmpty()) {
            currentPage = MATCH_SCORE_CONTROLLER.getMatchesByPlayerName(pageNum, playerName);
        } else currentPage = MATCH_SCORE_CONTROLLER.getMatchesByPage(pageNum);
        req.setAttribute("page", currentPage);
        req.getRequestDispatcher("/matches.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String servletPath = req.getServletPath();

        if (servletPath.equals("/matches")) {
            String pageNumParam = req.getParameter("page");
            int pageNum = 1;

            if (pageNumParam != null && !pageNumParam.isEmpty()) {
                pageNum = Integer.parseInt(pageNumParam);
            }
            String playerName = req.getParameter("filter_by_player_name");
            if (playerName != null && !playerName.trim().isEmpty()) {
                resp.sendRedirect(req.getContextPath() + "/matches?page=" + pageNum + "&filter_by_player_name=" + playerName);
            } else {
                resp.sendRedirect(req.getContextPath() + "/matches?page=" + pageNum);
            }
        }
    }
}