package ru.parsentev.servlets;

import com.sun.org.apache.regexp.internal.RE;
import ru.parsentev.models.User;
import ru.parsentev.store.UserCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * TODO: comment.
 * Created by Bogdan on 5/14/2016.
 */
public class UserViewServlet extends HttpServlet {

    private static final UserCache USER_CACHE = UserCache.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", USER_CACHE.users());
        req.getRequestDispatcher("/WEB-INF/views/user/ViewUser.jsp").forward(req, resp);
    }
}
