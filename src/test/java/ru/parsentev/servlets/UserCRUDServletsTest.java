package ru.parsentev.servlets;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.parsentev.models.User;
import ru.parsentev.store.StorageCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * TODO: comment.
 * Created by Bogdan on 6/3/2016.
 */
public class UserCRUDServletsTest {
    private StorageCache storage = StorageCache.getInstance();

    /**
     * Test method Get UserCreateServlet
     * @throws ServletException
     * @throws IOException
     */
    /*@Test
    public void whenExecuteGetUserCreateServletShouldReturnView() throws ServletException, IOException {
//        UserCreateServlet controller = new UserCreateServlet();
//        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);

//        when(request.getRequestDispatcher("/WEB-INF/views/user/CreateUser.jsp")).thenReturn(requestDispatcher);

//        controller.doGet(request, response);

//        verify(requestDispatcher).forward(request, response);
    }

    *
     * Test method Post UserCreateServlet
     * @throws ServletException
     * @throws IOException

    @Before
    public void whenExecutePostUserCreateServletShouldAddUserToStorage() throws ServletException, IOException {
        UserCreateServlet controller = new UserCreateServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("login")).thenReturn("Petr");

        controller.doPost(request, response);
        List<User> users = storage.getUsers();

//        assertThat(users.get(users.size()-1).getLogin(), is("Petr"));
    }

    *
     * Test method Get UserViewServlet
     * @throws ServletException
     * @throws IOException

    @Test
    public void whenExecuteGetUserViewServletShouldReturnView() throws ServletException, IOException {
        UserViewServlet controller = new UserViewServlet();
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("login")).thenReturn("root"); // get method expects data, use 'root' for example
        when(request.getRequestDispatcher("/WEB-INF/views/user/ViewUser.jsp")).thenReturn(requestDispatcher);

        controller.doGet(request, response);

//        verify(requestDispatcher).forward(request, response);
    }

    *
     * Test method Get UserEditServlet
     * @throws ServletException
     * @throws IOException

    @Test
    public void whenExecuteGetUserEditServletShouldReturnView() throws ServletException, IOException {
        UserEditServlet controller = new UserEditServlet();
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("id")).thenReturn("1"); // get method expects data, use '1' for example
        when(request.getRequestDispatcher("/WEB-INF/views/user/EditUser.jsp")).thenReturn(requestDispatcher);

        controller.doGet(request, response);

//        verify(requestDispatcher).forward(request, response);
    }

    *
     * Test method Post UserEditServlet
     * @throws ServletException
     * @throws IOException

    @Test
    public void whenExecutePostUserEditServletShouldEditUserInStorage() throws ServletException, IOException {
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

//        assertThat(lastUser.getName(), is("name2"));
//        assertThat(lastUser.getLogin(), is("Petr2"));
//        assertThat(lastUser.getPassword(), is("password2"));
//        assertThat(lastUser.getEmail(), is("email2"));
    }

    *
     * Test method Get UserDeleteServlet
     * @throws ServletException
     * @throws IOException

    @After
    public void whenExecuteGetUserDeleteServletShouldDeleteUserFromStorage() throws ServletException, IOException {
        UserDeleteServlet controller = new UserDeleteServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        Integer userIdCheck = storage.getUsers().get(storage.getUsers().size()-1).getId();
        when(request.getParameter("id")).thenReturn(userIdCheck.toString());

        controller.doGet(request, response);
//        assertThat(storage.getUsersIds().contains(userIdCheck), is(false));
    }*/
}