package ru.parsentev.servlets;

import ru.parsentev.models.Comment;
import ru.parsentev.store.StorageCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;

/**
 * TODO: comment.
 * Created by Bogdan on 8/3/2016.
 */
public class ItemCommentCreateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StorageCache.getInstance().addCommentToItem(Integer.valueOf(req.getParameter("id")), new Comment(req.getParameter("description"), Calendar.getInstance()));
        resp.sendRedirect(String.format("%s/user/view", req.getContextPath()));
    }
}
