package ru.parsentev.servlets;

import ru.parsentev.models.User;
import ru.parsentev.store.StorageCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TODO: comment.
 * Created by Bogdan on 5/14/2016.
 */
public class UserCreateServlet extends HttpServlet {

    private static final StorageCache USER_CACHE = StorageCache.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/user/CreateUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        USER_CACHE.addUser(new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("password"), req.getParameter("email")));
        resp.sendRedirect(String.format("%s/user/view", req.getContextPath()));
    }
}
