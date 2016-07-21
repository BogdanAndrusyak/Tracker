package ru.parsentev.servlets;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * TODO: comment.
 * Created by Bogdan on 7/21/2016.
 */
public class SignoutControllerTest {
    @Test
    public void whenExecuteGetSignoutControllerShouldInvalidateSession() throws ServletException, IOException {
        SignoutController controller = new SignoutController();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);

        controller.doGet(request, response);

        verify(session).invalidate();
    }
}