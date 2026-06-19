package com.roadmap.fourth;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.roadmap.fourth.model.ErrorModel;

@WebFilter(urlPatterns = "/*")
public class ErrorFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;

        try {
            chain.doFilter(req, resp);
        } catch (RuntimeException exception) {
            String title = exception.getClass().getSimpleName();
            String message = exception.getMessage();
            int httpStatus = 0;

            switch (exception.getClass().getSimpleName()) {
                case "NOT_FOUND": httpStatus = 404; break;
                case "BAD_REQUEST": httpStatus = 400; break;
                case "INTERNAL_SERVER_ERROR": httpStatus = 500; break;
            }

            ErrorModel error = new ErrorModel(title, message, httpStatus);
            req.setAttribute("errorModel", error);
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
