package ru.parsentev.servlets;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * TODO: comment.
 * Created by Bogdan on 5/16/2016.
 */
public class PermissionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        if (request.getRequestURI().equals(String.format("%s/", request.getContextPath()))) { // page only for administrator
            HttpSession session = request.getSession();
            synchronized (session) {
                if (session.getAttribute("roleId") != null && Integer.valueOf((Integer) session.getAttribute("roleId")) != 1) {
                    //req.setAttribute("id", session.getAttribute("roleId"));
                    ((HttpServletResponse) resp).sendRedirect(String.format("%s/user/edit?id=%s", request.getContextPath(), session.getAttribute("roleId")));
                    /*when wanna pass something in request should use getRequestDispatcher, not sendRedirect*/
                    return; // not to pass response for another filters?
                }
            }
            chain.doFilter(req, resp);
        } else {
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
