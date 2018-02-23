package ru.job4j.sepoperservlet;

import org.junit.Test;
import ru.job4j.crudservlet.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.atLeast;

/**
 * Edit and Delete servlets test.
 */
public class EditAndDeleteAdminServletTest {
    /**
     * test.
     * @throws ServletException - exc.
     * @throws IOException - exc.
     */
    @Test
    public void whenInvokeDoPostThenDeleteUserServletRun() throws ServletException, IOException {
        DeleteUserServlet servlet = new DeleteUserServlet();
        EditAdminServlet servletEdit = new EditAdminServlet();
        servlet.init();
        new CreateUserServletTest().whenInvokeDoPostThenCreateUserServletRun(); //create user(and then we'll delete him.

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        List<User> users = MyDataSource.getInstance().getUsers();
        assertThat(users.get(users.size() - 1).getEmail(), is("email@"));
        when(request.getParameter("name")).thenReturn("name");
        when(request.getParameter("login")).thenReturn("login");
        when(request.getParameter("email")).thenReturn("@email-new");
        when(request.getParameter("password")).thenReturn("password");
        when(request.getParameter("role")).thenReturn("user");
        servletEdit.doPost(request, response); //edit created user.

        List<User> usersAfterEdit = MyDataSource.getInstance().getUsers();
        assertThat(usersAfterEdit.get(users.size() - 1).getEmail(), is("@email-new"));

        assertThat(users.get(users.size() - 1).getLogin(), is("login"));
        when(request.getParameter("login")).thenReturn("login");
        servlet.doPost(request, response); //delete created + edited user.

        List<User> usersAfterDelete = MyDataSource.getInstance().getUsers();
        assertThat(usersAfterDelete.size(), is(usersAfterEdit.size() - 1));
        verify(request, atLeast(1)).getParameter("login");
    }
}