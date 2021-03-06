package ru.job4j.carstore.servlets;

import com.google.gson.JsonObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.FileCleanerCleanup;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileCleaningTracker;
import ru.job4j.carstore.dao.ImageDAO;
import ru.job4j.carstore.dao.OrderDAO;
import ru.job4j.carstore.models.Image;
import ru.job4j.carstore.models.Order;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;
/**
 * get all car images.
 */
public class ImageServlet extends HttpServlet {
    /**
     * imageDAO.
     */
    private ImageDAO imageDAO = ImageDAO.getInstance();
    /**
     * get.
     * @param req - req.
     * @param resp - resp.
     * @throws ServletException - exc.
     * @throws IOException - exc.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("image/jpeg");
        Order order = OrderDAO.getINSTANCE().getById(Integer.valueOf(req.getParameter("order")));
        List<Image> list =  order.getImages();
        List<String> newList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = resp.getWriter();
        for (Image nextImage : list) {
            newList.add(String.format("data:image/jpeg;base64,%s", DatatypeConverter.printBase64Binary(nextImage.getData())));
        }
        writer.append(mapper.writeValueAsString(newList));
        writer.flush();
    }
    /**
     * post.
     * @param req - req.
     * @param resp - resp.
     * @throws ServletException - exc.
     * @throws IOException - exc.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean isMultipartContent = ServletFileUpload.isMultipartContent(req);
        HttpSession session = req.getSession(false);
        Integer userId = (int) session.getAttribute("user_id");
        Integer orderId = (int) session.getAttribute("order_id");
        OrderDAO orderDAO = OrderDAO.getINSTANCE();
        Order order = orderDAO.getById(orderId);
        PrintWriter writer = resp.getWriter();
        JsonObject object = new JsonObject();
        object.addProperty("success", false);
        if (isMultipartContent && userId != -1 && orderId != -1) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletContext context = this.getServletConfig().getServletContext();

            FileCleaningTracker tracker = FileCleanerCleanup.getFileCleaningTracker(context);
            factory.setFileCleaningTracker(tracker);
            File repository = (File) context.getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(repository);

            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                List<FileItem> items = upload.parseRequest(req);
                for (FileItem item : items) {
                    byte[] imageData = item.get();
                    Image image = new Image();
                    image.setData(imageData);
                    image.setOrder(order);
                    imageDAO.add(image);
                    order.getImages().add(image);

                }
                object.addProperty("success", true);
            } catch (FileUploadException fue) {
                fue.printStackTrace();
            }
        }
        writer.append(object.toString());
        writer.flush();
    }
}
