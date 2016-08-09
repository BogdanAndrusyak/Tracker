package ru.parsentev.servlets;

import ru.parsentev.models.User;
import ru.parsentev.store.StorageCache;

import javax.servlet.RequestDispatcher;
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
public class UserCreateServlet extends HttpServlet {

    private static final StorageCache USER_CACHE = StorageCache.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/user/CreateUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        String city = req.getParameter("city");

        //if invalidate data -
        if (name.equals("") || login.equals("") || password.equals("") || email.equals("") || country.equals("") || city.equals("")) {
            req.setAttribute("error", "Error. Blank fields");
            doGet(req, resp);
        } else {
            USER_CACHE.addUser(new User(name, login, password, email, country, city));

            HttpSession session = req.getSession();
            synchronized (session) {
                session.setAttribute("login", req.getParameter("login"));
                session.setAttribute("roleId", USER_CACHE.findByLogin(req.getParameter("login")).getRole().getId()); //todo can does not using, only user variable
            }
            req.getRequestDispatcher("/WEB-INF/views/user/ViewUser.jsp").forward(req, resp);
        }
    }
}
