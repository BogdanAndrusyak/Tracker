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
public class UserViewServlet extends HttpServlet {

    private static final UserCache USER_CACHE = UserCache.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());

        StringBuilder sb = new StringBuilder("<a href=\"" + String.format("%s%s", req.getContextPath(), "/user/create")+"\">Add new user</a><br><br>" +
                "<table style=\"border: 1px solid #000\" border=\"1\" cellpadding=\"1\" cellspacing=\"0\">" +
                "        <tr>" +
                "            <th>Id</th>" +
                "            <th>Name</th>" +
                "            <th>Login</th>" +
                "            <th>Email</th>" +
                "            <th></th>" +
                "        </tr>");
        for (User user : USER_CACHE.users()) {
            sb.append(
                    "<tr>" +
                    "   <td>" + user.getId() + "</td>" +
                    "   <td>" + user.getName() + "</td>" +
                    "   <td>" + user.getLogin() + "</td>" +
                    "   <td>" + user.getEmail() + "</td>" +
                    "   <td>" +
                    "        <form action=\"" + req.getContextPath() + "/user/edit\" method=\"get\">" +
                    "            <input type=\"hidden\" name=\"id\" value=\"" + user.getId() + "\">" +
                    "            <input type=\"submit\" value=\"Edit\">" +
                    "        </form>" +
                    "       <form action=\"" + req.getContextPath() + "/user/delete\" method=\"post\">" +
                    "            <input type=\"hidden\" name=\"id\" value=\"" + user.getId() + "\">" +
                    "            <input type=\"submit\" value=\"Delete\">" +
                    "        </form>" +
                    "   </td>" +
                    "</tr>"
            );
        }
        sb.append("</table>");

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
}
