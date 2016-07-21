package ru.parsentev.servlets;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * TODO: comment.
 * Created by Bogdan on 7/21/2016.
 */
public class SigninControllerTest {
    @Test
    public void whenExecuteGetSigninControllerShouldReturnView() throws ServletException, IOException {
        SigninController controller = new SigninController();
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getRequestDispatcher("/WEB-INF/views/user/LoginView.jsp")).thenReturn(requestDispatcher);

        controller.doGet(request, response);

        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void whenExecutePostSigninControllerWithCorrectCredentialShouldAddToSessionDataAboutUser() throws ServletException, IOException {
        //Data for test from db
        String login = "root";
        String password = "root";
        int roleId = 1; // id for administrator

        SigninController controller = new SigninController();
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getParameter("login")).thenReturn(login);
        when(request.getParameter("password")).thenReturn(password);
        when(request.getSession()).thenReturn(session);

        controller.doPost(request, response);

        verify(session).setAttribute("login", login);
        verify(session).setAttribute("roleId", roleId);
    }

    @Test
    public void whenExecutePostSigninControllerWithNotCorrectCredentialShouldReturnViewWithError() throws ServletException, IOException {
        //Data for test (not credential)
        String login = "root";
        String password = "root223415";
        int roleId = 1; // id for administrator

        SigninController controller = new SigninController();
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("login")).thenReturn(login);
        when(request.getParameter("password")).thenReturn(password);
        when(request.getRequestDispatcher("/WEB-INF/views/user/LoginView.jsp")).thenReturn(requestDispatcher);

        controller.doPost(request, response);

        verify(request).setAttribute("error", "Credential invalid");
        verify(requestDispatcher).forward(request, response);
    }
}