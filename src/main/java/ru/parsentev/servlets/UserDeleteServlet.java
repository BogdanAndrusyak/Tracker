package ru.parsentev.servlets;

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
public class UserDeleteServlet extends HttpServlet {

    private static final StorageCache USER_CACHE = StorageCache.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        USER_CACHE.deleteUser(Integer.valueOf(req.getParameter("id")));
        resp.sendRedirect(String.format("%s/user/view", req.getContextPath()));
    }
}
