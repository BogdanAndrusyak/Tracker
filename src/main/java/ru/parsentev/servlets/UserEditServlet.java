package ru.parsentev.servlets;

import ru.parsentev.models.Role;
import ru.parsentev.models.User;
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
public class UserEditServlet extends HttpServlet {

    private static final StorageCache USER_CACHE = StorageCache.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
//        synchronized (session) {
//            if (((Integer)session.getAttribute("roleId")) != 1) {
//
//            }
//        }
        req.setAttribute("user", USER_CACHE.getUserById(Integer.valueOf(req.getParameter("id"))));
        req.setAttribute("roles", USER_CACHE.getRoles());
        req.getRequestDispatcher("/WEB-INF/views/user/EditUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        USER_CACHE.editUser(new User(Integer.valueOf(req.getParameter("id")), new Role(Integer.valueOf(req.getParameter("role-id"))),
                req.getParameter("name"), req.getParameter("login"), req.getParameter("password"), req.getParameter("email")));
        resp.sendRedirect(String.format("%s/user/view", req.getContextPath()));
    }
}
