package ru.job4j.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.job4j.dao.BrandDAO;
import ru.job4j.dao.OrderDAO;
import ru.job4j.dao.UserDAO;
import ru.job4j.models.Brand;
import ru.job4j.models.Image;
import ru.job4j.models.Order;
import ru.job4j.models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * operations on index page.
 */
@Controller
public class CarStoreController {

    private static final Logger LOG = LoggerFactory.getLogger(CarStoreController.class);
    /**
     * orderDAO.
     */
    private final OrderDAO orderDAO;
    /**
     * userDAO.
     */
    private final UserDAO userDAO;
    /**
     * brandDAO.
     */
    private final BrandDAO brandDAO;

    /**
     * Constructor.
     * @param orderDAO - orderDAO.
     * @param userDAO - userDAO.
     * @param brandDAO - brandDAO.
     */
    @Autowired
    public CarStoreController(OrderDAO orderDAO, UserDAO userDAO, BrandDAO brandDAO) {
        this.orderDAO = orderDAO;
        this.userDAO = userDAO;
        this.brandDAO = brandDAO;

    }

    /**
     * start page.
     * @param req - req.
     * @param session - session.
     * @param model - model.
     * @return - index page.
     * @throws IOException - exc.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showUsers(HttpServletRequest req, HttpSession session, ModelMap model) throws IOException {
        List<Brand> brands = (List<Brand>) brandDAO.findAll();
        model.addAttribute("brands", brands);
        Boolean lastDay = Boolean.valueOf((String) session.getAttribute("data"));
        Boolean withFoto = Boolean.valueOf((String) session.getAttribute("foto"));
        String brand = req.getParameter("brand");
        List<Order> list = (List<Order>) orderDAO.findAll();
        if (lastDay != null && withFoto != null && brand != null) {
            List<Order> newList = new ArrayList<>();
            if (lastDay) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                long dayStart = calendar.getTimeInMillis();
                newList = orderDAO.findAllByDateGreaterThan(new Timestamp(dayStart));
                list = newList;
            }
            if (withFoto) {
                for (Order nextOrder : list) {
                    if (nextOrder.getImages().size() > 0) {
                        newList.add(nextOrder);
                    }
                }
                list = newList;
            }
            if (!brand.equals("")) {
                newList = orderDAO.findAllByCarModelBrandName(brand);
                list = newList;
            }
        }

        model.addAttribute("orders", list);

        return "index";
    }


    /**
     * log in.
     * @param login - login.
     * @param password - password.
     * @param session - session.
     * @param model - model.
     * @return - user, if input valid.
     * @throws IOException - exc.
     */
    @RequestMapping(value = "/signin", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String signIn(@RequestParam("login") String login, @RequestParam("password") String password, HttpSession session, ModelMap model) throws IOException {
        session.setAttribute("user", null);
        User validUser = null;
        List<User> list = (List<User>) userDAO.findAll();
        for (User next : list) {
            if (next.getLogin().equals(login)
                    && next.getPassword().equals(password)) {
                validUser = next;
                session.setAttribute("user", next);
                session.setAttribute("user_id", next.getId());
                session.setAttribute("login", next.getLogin());
                break;
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(validUser);
    }

    /**
     * check current user.
     * @param session - session.
     * @return - user id or -1.
     * @throws IOException - exc.
     */
    @RequestMapping(value = "/valid", produces = "applacation/json")
    @ResponseBody
    public String valid(HttpSession session) throws IOException {
        Integer id;
        if (session == null) {
            id = -1;
        } else {
            if (session.getAttribute("user_id") == null) {
                System.out.println("Hi");
            }
            id = (Integer) session.getAttribute("user_id");
            if (id == null) {
                id = -1;
            }
        }
        return String.valueOf(id);
    }

    /**
     * check currents user login.
     * @param session - session.
     * @return - login of current user.
     * @throws IOException - exc.
     */
    @RequestMapping(value = "/currentUser", produces = "applacation/json")
    @ResponseBody
    public String currentUser(HttpSession session) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String login = (String) session.getAttribute("login");
        if (login == null) {
            login = "Guest";
        }
        return mapper.writeValueAsString(login);
    }

    /**
     * log out.
     * @param session - session
     * @throws IOException - exc.
     */
    @RequestMapping(value = "/logout")
    public void logOut(HttpSession session) throws IOException {
        session.invalidate();
    }

    /**
     * set foto filter state.
     * @param session -  session.
     * @throws IOException - exc.
     */
    @RequestMapping(value = "/fotoReverse")
    public void fotoReverse(HttpSession session) throws IOException {
        if (session.getAttribute("foto") == null) {
            session.setAttribute("foto", "true");
        } else {
            session.setAttribute("foto", null);
        }
    }

    /**
     * set data filter state.
     * @param session -  session.
     * @throws IOException - exc.
     */
    @RequestMapping(value = "/dataReverse")
    public void dataReverse(HttpSession session) throws IOException {
        if (session.getAttribute("data") == null) {
            session.setAttribute("data", "true");
        } else {
            session.setAttribute("data", null);
        }

    }

    /**
     * reset filter.
     * @param session -  session.
     * @throws IOException - exc.
     */
    @RequestMapping(value = "/resetFilter")
    public void filterReset(HttpSession session) throws IOException {
        session.setAttribute("foto", null);
        session.setAttribute("data", null);
    }

    /**
     * get order images.
     * @param orderId - order id.
     * @return - list of images.
     * @throws IOException - exc.
     */
    @RequestMapping(value = "/image", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String showImage(@RequestParam("order") String orderId) throws IOException {
        Order order = orderDAO.findById(Integer.valueOf(orderId)).get();
        List<Image> list = order.getImages();
        List<String> newList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for (Image nextImage : list) {
            newList.add(String.format("data:image/jpeg;base64,%s", DatatypeConverter.printBase64Binary(nextImage.getData())));
        }
        return mapper.writeValueAsString(newList);
//        JsonArray array = new JsonArray();
//        Order order = orderDAO.findById(Integer.valueOf(orderId)).get();
//        List<Image> list = order.getImages();
//        List<String> newList = new ArrayList<>();
//
//        for (Image image:list) {
//            array.add(String.format("data:image/jpeg;base64,%s", DatatypeConverter.printBase64Binary(image.getData())));
//        }
//        return array.toString();
    }
}
