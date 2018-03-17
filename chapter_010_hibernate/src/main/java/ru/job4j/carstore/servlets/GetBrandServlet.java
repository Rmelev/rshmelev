package ru.job4j.carstore.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.carstore.dao.BrandDAO;
import ru.job4j.carstore.models.Brand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
/**
 * get all car brands.
 */
public class GetBrandServlet extends HttpServlet {
    /**
     * get.
     * @param req - req.
     * @param resp - resp.
     * @throws ServletException - exc.
     * @throws IOException - exc.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        List<Brand> list = BrandDAO.getInstance().getAll();
        PrintWriter writer = resp.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        writer.append(mapper.writeValueAsString(list));
        writer.flush();
    }
}
