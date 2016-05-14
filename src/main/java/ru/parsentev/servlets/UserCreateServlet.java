package ru.parsentev.servlets;

import ru.parsentev.models.User;
import ru.parsentev.store.UserCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * TODO: comment.
 * Created by Bogdan on 5/14/2016.
 */
public class UserCreateServlet extends HttpServlet {

    private static final UserCache USER_CACHE = UserCache.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());

        StringBuilder sb = new StringBuilder("<a href=\"" + String.format("%s%s", req.getContextPath(), "/user/view") + "\">back</a><br><br>" +
                "<form action=\"" + String.format("%s%s", req.getContextPath(), "/user/create")+ "\" method=\"post\">" +
                "   Name: <input type=\"text\" name=\"name\"><br>" +
                "   Login: <input type=\"text\" name=\"login\"><br>" +
                "   Email: <input type=\"text\" name=\"email\"><br>" +
                "   <input type=\"submit\" value=\"Create\">" +
                "</form>");

        writer.append("<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <title></title>" +
                "</head>" +
                "<body>" +
                sb.toString() +
                "</body>" +
                "</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        USER_CACHE.addUser(new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email")));
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/user/view"));
    }
}
