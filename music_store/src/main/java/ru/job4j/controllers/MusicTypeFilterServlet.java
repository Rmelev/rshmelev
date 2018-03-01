package ru.job4j.controllers;

import ru.job4j.models.MusicType;
import ru.job4j.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MusicTypeFilterServlet extends UserServletBase  {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameter = req.getParameter("mtype");
        List<User> userList = repository.getAll();
        List<User> musicTypeFilterList = new ArrayList<>();
        for (User next :userList) {
            List<MusicType> oneUserMusicTypeList = next.getMusicTypes();
            for (MusicType nextMT : oneUserMusicTypeList) {
                if ((nextMT.getType()).equals(parameter)) {
                    musicTypeFilterList.add(next);
                }
            }
        }
        req.setAttribute("mtype", parameter);
        HttpSession session = req.getSession();
        session.setAttribute("musicTypeRoleFilter", musicTypeFilterList);
        req.getRequestDispatcher("/WEB-INF/views/filtered.jsp").forward(req, resp);
    }
}
