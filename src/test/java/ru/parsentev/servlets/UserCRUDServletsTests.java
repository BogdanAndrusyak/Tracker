package ru.parsentev.servlets;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.parsentev.models.User;
import ru.parsentev.store.StorageCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * TODO: comment.
 * Created by Bogdan on 6/3/2016.
 */
public class UserCRUDServletsTests {
    StorageCache storage = StorageCache.getInstance();

    @Before
    public void addUser() throws ServletException, IOException {
        UserCreateServlet controller = new UserCreateServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse responce = mock(HttpServletResponse.class);

        when(request.getParameter("login")).thenReturn("Petr");

        controller.doPost(request, responce);
        List<User> users = storage.getUsers();

        assertThat(users.get(users.size()-1).getLogin(), is("Petr"));
    }

    @Test
    public void editUser() throws ServletException, IOException {
        UserEditServlet controller = new UserEditServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse responce = mock(HttpServletResponse.class);

        Integer userId = storage.getUsers().get(storage.getUsers().size()-1).getId();
        when(request.getParameter("id")).thenReturn(userId.toString());
        when(request.getParameter("role-id")).thenReturn("2"); //todo

        when(request.getParameter("name")).thenReturn("name2");
        when(request.getParameter("login")).thenReturn("Petr2");
        when(request.getParameter("password")).thenReturn("password2");
        when(request.getParameter("email")).thenReturn("email2");

        controller.doPost(request, responce);
        User lastUser = storage.getUsers().get(storage.getUsers().size()-1);

        assertThat(lastUser.getName(), is("name2"));
        assertThat(lastUser.getLogin(), is("Petr2"));
        assertThat(lastUser.getPassword(), is("password2"));
        assertThat(lastUser.getEmail(), is("email2"));
    }

    @After
    public void deleteUser() throws ServletException, IOException {
        UserDeleteServlet controller = new UserDeleteServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        Integer userIdCheck = storage.getUsers().get(storage.getUsers().size()-1).getId();
        when(request.getParameter("id")).thenReturn(userIdCheck.toString());

        controller.doGet(request, response);
        assertThat(storage.getUsersIds().contains(userIdCheck), is(false));
    }
}