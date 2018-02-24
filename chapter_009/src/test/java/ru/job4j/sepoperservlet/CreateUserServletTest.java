package ru.job4j.sepoperservlet;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.crudservlet.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.never;

/**
 * Create servlets test.
 */
public class CreateUserServletTest {
    /**
     * path.
     */
    private static final String PATH = "WEB-INF/views/create.jsp";
    /**
     * req.
     */
    private HttpServletRequest request = mock(HttpServletRequest.class);
    /**
     * resp.
     */
    private HttpServletResponse response = mock(HttpServletResponse.class);
    /**
     * disp.
     */
    private RequestDispatcher dispatcher = mock(RequestDispatcher.class);
    /**
     * create servlet.
     */
    private CreateUserServlet servlet = new CreateUserServlet();
    /**
     * database.
     */
    private DAO dao = new DAO();

    /**
     * before action.
     */
    @Before
    public void before() {
        servlet.init();
    }

    /**
     * test.
     * @throws ServletException - exc.
     * @throws IOException - exc.
     */
    @Test
    public void whenInvokeDoGetThenCreateUserServletRun() throws ServletException, IOException {
        when(request.getRequestDispatcher(PATH)).thenReturn(dispatcher);
        when(request.getRequestDispatcher(PATH)).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(request).getRequestDispatcher(PATH);
        verify(request, never()).getSession();
        verify(dispatcher).forward(request, response);
    }
    /**
     * test.
     * @throws ServletException - exc.
     * @throws IOException - exc.
     */
    @Test
    public void whenInvokeDoPostThenCreateUserServletRun() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("name")).thenReturn("name");
        when(request.getParameter("login")).thenReturn("login");
        when(request.getParameter("email")).thenReturn("email@");
        when(request.getParameter("password")).thenReturn("password");
        when(request.getParameter("role")).thenReturn("user");
        when(request.getParameter("country")).thenReturn("country");
        when(request.getParameter("city")).thenReturn("city");

        servlet.doPost(request, response);

        verify(request, atLeast(1)).getParameter("login");
        List<User> users = dao.getUsers();
        assertThat(users.get(users.size() - 1).getLogin(), is("login"));
        assertEquals("gora", users.get(0).getLogin());
        assertThat(users.get(users.size() - 1).getPassword(), is("password"));
    }
  }