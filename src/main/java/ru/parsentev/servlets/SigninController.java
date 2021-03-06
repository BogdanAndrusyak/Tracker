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
 * Created by Bogdan on 5/15/2016.
 */
public class SigninController extends HttpServlet{

    private static final StorageCache USER_CACHE = StorageCache.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/user/LoginView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        //validate data?
        if (login.equals("") || password.equals("") || !USER_CACHE.isCredential(login, password)) {
            req.setAttribute("error", "Credential invalid");
            doGet(req, resp);
        } else {
            HttpSession session = req.getSession();
            synchronized (session) {
                session.setAttribute("login", login);
                session.setAttribute("roleId", USER_CACHE.findByLogin(login).getRole().getId()); //todo can does not using, only user variable
            }
            resp.sendRedirect(String.format("%s/user/view", req.getContextPath()));
        }
    }
}
