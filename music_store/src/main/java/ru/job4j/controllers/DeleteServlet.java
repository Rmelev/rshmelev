package ru.job4j.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteServlet extends UserServletBase {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        repository.delete(Integer.valueOf(req.getParameter("el_id")));
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
