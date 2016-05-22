package ru.parsentev.servlets;

import ru.parsentev.models.Item;
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
 * Created by Bogdan on 5/19/2016.
 */
public class ItemCreateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        synchronized (session) {
            StorageCache.getInstance().addItem(new Item(req.getParameter("itemName"), (StorageCache.getInstance().findByLogin((String) session.getAttribute("login"))).getId()));
        }
        resp.sendRedirect(String.format("%s/user/view", req.getContextPath()));
    }
}
