package ru.job4j.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.FileCleanerCleanup;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileCleaningTracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.job4j.dao.BodyDAO;
import ru.job4j.dao.BrandDAO;
import ru.job4j.dao.CarDAO;
import ru.job4j.dao.EngineDAO;
import ru.job4j.dao.ImageDAO;
import ru.job4j.dao.ModelDAO;
import ru.job4j.dao.OrderDAO;
import ru.job4j.dao.TransmissionDAO;
import ru.job4j.models.Body;
import ru.job4j.models.Brand;
import ru.job4j.models.Car;
import ru.job4j.models.Engine;
import ru.job4j.models.Image;
import ru.job4j.models.Model;
import ru.job4j.models.Order;
import ru.job4j.models.Transmission;
import ru.job4j.models.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * operation on add page(add order).
 */
@Controller
public class AddOrderController {
    /**
     * orderDAO.
     */
    private final OrderDAO orderDAO;
    /**
     * brandDAO.
     */
    private final BrandDAO brandDAO;
    /**
     * modelDAO.
     */
    private final ModelDAO modelDAO;
    /**
     * engineDAO.
     */
    private final EngineDAO engineDAO;
    /**
     * transmissionDAO.
     */
    private final TransmissionDAO transmissionDAO;
    /**
     * bodyDAO.
     */
    private final BodyDAO bodyDAO;
    /**
     * imageDAO.
     */
    private final ImageDAO imageDAO;
    /**
     * carDAO.
     */
    private final CarDAO carDAO;

    /**
     * Constructor.
     * @param orderDAO - orderDAO.
     * @param brandDAO - brandDAO.
     * @param modelDAO - modelDAO.
     * @param engineDAO - engineDAO.
     * @param transmissionDAO - transmissionDAO.
     * @param bodyDAO - bodyDAO.
     * @param imageDAO - imageDAO.
     * @param carDAO - carDAO.
     */
    @Autowired
    public AddOrderController(OrderDAO orderDAO, BrandDAO brandDAO,
                              ModelDAO modelDAO, EngineDAO engineDAO,
                              TransmissionDAO transmissionDAO, BodyDAO bodyDAO,
                              ImageDAO imageDAO, CarDAO carDAO) {
        this.orderDAO = orderDAO;
        this.brandDAO = brandDAO;
        this.modelDAO = modelDAO;
        this.engineDAO = engineDAO;
        this.transmissionDAO = transmissionDAO;
        this.bodyDAO = bodyDAO;
        this.imageDAO = imageDAO;
        this.carDAO = carDAO;
    }

    /**
     * show add page.
     * @return - add.jsp.
     * @throws IOException = exc.
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showUsers() throws IOException {
        return "add";
    }

    /**
     * add order.
     * @param order - order.
     * @param session - session.
     * @param color - color.
     * @param model - model.
     * @param body - body.
     * @param transmission - transmission.
     * @param engine - engine.
     * @return - add.jsp.
     * @throws IOException - exc.
     */
    @RequestMapping(value = "/addOrder", method = RequestMethod.POST, consumes = "application/json")
    public String addOrder(@ModelAttribute Order order, HttpSession session, @RequestParam("color") String color,
                           @RequestParam("model") String model, @RequestParam("body") String body,
                           @RequestParam("transmission") String transmission, @RequestParam("engine") String engine) throws IOException {

        Car car = new Car();
        car.setColor(color);
        car.setModel(modelDAO.getByName(model));
        car.setBody(bodyDAO.getByName(body));
        car.setEngine(engineDAO.getByName(engine));
        car.setTransmission(transmissionDAO.getByName(transmission));
        carDAO.add(car);
        order.setUser((User) session.getAttribute("user"));
        order.setDate(new Timestamp(System.currentTimeMillis()));
        order.setCar(car);

        int orderId = orderDAO.add(order);
        session.setAttribute("order_id", orderId);

        return "add";
    }

    /**
     * add image.
     * @param session - session.
     * @param req - req.
     * @return - true, if success operation.
     * @throws IOException - exc.
     * @throws ServletException - exc.
     */
    @RequestMapping(value = "/addImage", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ObjectNode addImage(HttpSession session, HttpServletRequest req) throws IOException, ServletException {
        boolean isMultipartContent = ServletFileUpload.isMultipartContent(req);
        Integer userId = (int) session.getAttribute("user_id");
        Integer orderId = (int) session.getAttribute("order_id");
        OrderDAO orderDAO = OrderDAO.getINSTANCE();
        Order order = orderDAO.getById(orderId);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("success", false);
//        JsonObject object = new JsonObject();
//        object.addProperty("success", false);

        if (isMultipartContent && userId != -1 && orderId != -1) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletContext context = ((ServletRequestAttributes) RequestContextHolder.
                    getRequestAttributes()).getRequest().getServletContext();

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
                node.put("success", true);
//                object.addProperty("success", true);
            } catch (FileUploadException fue) {
                fue.printStackTrace();
            }
        }

        return node;
    }

    /**
     * get all brands from db.
     * @return - brands.
     * @throws IOException - exc.
     */
    @RequestMapping(value = "/getBrands", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getBrands() throws IOException {
        List<Brand> list = brandDAO.getAll();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(list);
    }

    /**
     * get all transmissions from db.
     * @return - transmissions.
     * @throws IOException - exc.
     */
    @RequestMapping(value = "/getTransmissions", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getTransmissions() throws IOException {
        List<Transmission> list = transmissionDAO.getAll();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(list);
    }
    /**
     * get all models from db.
     * @param brand - brand.
     * @return - models.
     * @throws IOException - exc.
     */
    @RequestMapping(value = "/getModels", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getModels(@RequestParam("brand") String brand) throws IOException {
        List<Model> list = modelDAO.getAll();
        List<Model> newlist = new ArrayList<>();
        for (Model next : list) {
            if (next.getBrand().getName().equals(brand)) {
                newlist.add(next);
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(newlist);
    }
    /**
     * get all engines from db.
     * @return - engines.
     * @throws IOException - exc.
     */
    @RequestMapping(value = "/getEngines", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getEngines() throws IOException {
        List<Engine> list = engineDAO.getAll();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(list);
    }
    /**
     * get all bodies from db.
     * @return - bodies.
     * @throws IOException - exc.
     */
    @RequestMapping(value = "/getBodies", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getBodies() throws IOException {
        List<Body> list = bodyDAO.getAll();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(list);
    }


}
