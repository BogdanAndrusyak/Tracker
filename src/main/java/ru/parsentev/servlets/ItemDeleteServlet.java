package ru.parsentev.servlets;

import ru.parsentev.store.StorageCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TODO: comment.
 * Created by Bogdan on 5/22/2016.
 */
public class ItemDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StorageCache.getInstance().deleteItem(Integer.valueOf(req.getParameter("item-id")));
        resp.sendRedirect(String.format("%s/user/view", req.getContextPath()));
    }
}
