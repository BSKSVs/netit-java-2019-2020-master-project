package framework.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class WebController {

    private String absolutePath = "http://localhost:8182/JavaWebShop2_war_exploded";

    public void redirect(HttpServletResponse resp, String path)
    throws IOException {

        String redirectPath = absolutePath + path;

        resp.sendRedirect(redirectPath);
    }

    public void display(HttpServletRequest req, HttpServletResponse resp, String jspPage)
    throws ServletException, IOException {

        String viewPath = "/" + jspPage;
        req.getRequestDispatcher(viewPath).forward(req, resp);
    }

    public void session(HttpServletRequest req, String key, Object value) {
        req.getSession().setAttribute(key, value);
    }
}