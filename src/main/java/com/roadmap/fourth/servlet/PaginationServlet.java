package com.roadmap.fourth.servlet;

import com.roadmap.fourth.MatchScoreController;
import com.roadmap.fourth.exception.INTERNAL_SERVER_ERROR;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pageNumParam = req.getParameter("page");
        String playerName = req.getParameter("filter_by_player_name");
        Page currentPage;
        int pageNum = 1; // изначально наша страница = 1 (это сделано чтобы в случае ошибки просто не менять её параметр)

        if (pageNumParam != null && !pageNumParam.isEmpty()) {
            boolean isContainsLetter = false;
            for (char c : pageNumParam.toCharArray()) {
                if (Character.isLetter(c)) {
                    isContainsLetter = true;
                    break;
                }
            }
            if (!isContainsLetter) {
                int parsedPageNum = Integer.parseInt(pageNumParam);
                if (parsedPageNum > 0) {
                    pageNum = parsedPageNum;
                }
            }
        }

        if (playerName != null && !playerName.trim().isEmpty()) {
            currentPage = MATCH_SCORE_CONTROLLER.getMatchesByPlayerName(pageNum, playerName);
        } else currentPage = MATCH_SCORE_CONTROLLER.getMatchesByPage(pageNum);

        try {
            req.setAttribute("page", currentPage);
            req.getRequestDispatcher("/matches.jsp").forward(req, resp);
        } catch (ServletException e) {
            throw new INTERNAL_SERVER_ERROR("Failed to load matches page");
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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