package ru.job4j.controllers;

import ru.job4j.models.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RoleFilterServlet extends UserServletBase {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameter = req.getParameter("role");
        List<User> userList = repository.getAll();
        List<User> roleFilterList = new ArrayList<>();
        for (User next :userList) {
            if ((next.getRole().getRole()).equals(parameter)) {
                roleFilterList.add(next);
            }
        }
        req.setAttribute("role", parameter);
        HttpSession session = req.getSession();
        session.setAttribute("userRoleFilter", roleFilterList);
        req.getRequestDispatcher("/WEB-INF/views/filtered.jsp").forward(req, resp);
    }
}
