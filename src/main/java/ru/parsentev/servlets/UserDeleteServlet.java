package ru.parsentev.servlets;

import ru.parsentev.store.UserCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TODO: comment.
 * Created by Bogdan on 5/14/2016.
 */
public class UserDeleteServlet extends HttpServlet {

    private static final UserCache USER_CACHE = UserCache.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        USER_CACHE.deleteUser(Integer.valueOf(req.getParameter("id")));
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
