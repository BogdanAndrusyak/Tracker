package ru.parsentev.servlets;

import ru.parsentev.store.StorageCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * TODO: comment.
 * Created by Bogdan on 5/14/2016.
 */
public class UserViewServlet extends HttpServlet {

    private static final StorageCache USER_CACHE = StorageCache.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", USER_CACHE.users());
        HttpSession session = req.getSession();
        synchronized (session) {
            req.setAttribute("user", USER_CACHE.findByLogin((String) session.getAttribute("login"))); //todo maybe not security??
        }
        req.setAttribute("roles", USER_CACHE.getRoles());
        req.getRequestDispatcher("/WEB-INF/views/user/ViewUser.jsp").forward(req, resp);
    }
}
