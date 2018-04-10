package ru.job4j.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.repository.BodyDAO;
import ru.job4j.repository.BrandDAO;
import ru.job4j.repository.CarDAO;
import ru.job4j.repository.EngineDAO;
import ru.job4j.repository.ImageDAO;
import ru.job4j.repository.ModelDAO;
import ru.job4j.repository.OrderDAO;
import ru.job4j.repository.TransmissionDAO;
import ru.job4j.domain.Body;
import ru.job4j.domain.Brand;
import ru.job4j.domain.Car;
import ru.job4j.domain.Engine;
import ru.job4j.domain.Image;
import ru.job4j.domain.Model;
import ru.job4j.domain.Order;
import ru.job4j.domain.Transmission;
import ru.job4j.domain.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
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
     * Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(AddOrderController.class);

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
     * @param request - req.
     * @param session - session.
     * @return - add.jsp.
     */
    @Transactional
    @PostMapping(value = "/addOrder", consumes = "application/json;charset=UTF-8")
    public String addOrder(HttpServletRequest request, HttpSession session) {

        String jsonBody = "fff";
        // interesting part for control request content.
        try {
            jsonBody = IOUtils.toString(request.getInputStream());
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }

        ObjectMapper mapper = new ObjectMapper();
        Order obj = null;
        try {
            obj = mapper.readValue(jsonBody, Order.class);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        obj.setDate(new Timestamp(System.currentTimeMillis()));
        obj.setUser((User) session.getAttribute("user"));
        Car car = obj.getCar();
        Model md = modelDAO.getModelByName(car.getModel().getName());
        Body bd = bodyDAO.getBodyByName(car.getBody().getName());
        Transmission td = transmissionDAO.getTransmissionByName(car.getTransmission().getName());
        Engine ed = engineDAO.getEngineByName(car.getEngine().getName());
        car.setBody(bd);
        car.setModel(md);
        car.setTransmission(td);
        car.setEngine(ed);
        carDAO.save(car);
        int orderId = orderDAO.save(obj).getId();
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
    @PostMapping(value = "/addImage")
    public String addImage(HttpSession session, HttpServletRequest req, @RequestParam("file") MultipartFile file) throws IOException {

        boolean isMultipartContent = ServletFileUpload.isMultipartContent(req);
        Integer userId = (int) session.getAttribute("user_id");
        Integer orderId = (int) session.getAttribute("order_id");
        Order order = null;
        if (orderDAO.findById(orderId).isPresent()) {
            order = orderDAO.findById(orderId).get();
        }

        Image image = new Image();
        image.setData(file.getBytes());
        image.setOrder(order);
        imageDAO.save(image);
        order.getImages().add(image);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("success", true);

        return "add";
    }

    /**
     * get all brands from db.
     * @return - brands.
     * @throws IOException - exc.
     */
    @RequestMapping(value = "/getBrands", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getBrands() throws IOException {
        List<Brand> list = (List<Brand>) brandDAO.findAll();
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
        List<Transmission> list = (List<Transmission>) transmissionDAO.findAll();
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
        List<Model> list = (List<Model>) modelDAO.findAll();
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
        List<Engine> list = (List<Engine>) engineDAO.findAll();
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
        List<Body> list = (List<Body>) bodyDAO.findAll();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(list);
    }
}
