package ru.job4j.carstore.dao;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.carstore.models.Body;
import ru.job4j.carstore.models.Brand;
import ru.job4j.carstore.models.Car;
import ru.job4j.carstore.models.Engine;
import ru.job4j.carstore.models.Image;
import ru.job4j.carstore.models.Model;
import ru.job4j.carstore.models.Order;
import ru.job4j.carstore.models.Transmission;
import ru.job4j.carstore.models.User;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * test.
 */
public class OrderDAOTest {

    /**
     * logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(OrderDAOTest.class);
    /**
     * orderDAO.
     */
    private final OrderDAO orderDAO = OrderDAO.getINSTANCE();
    /**
     * bodyDAO.
     */
    private final BodyDAO bodyDAO = BodyDAO.getInstance();
    /**
     * brandDAO.
     */
    private final BrandDAO brandDAO = BrandDAO.getInstance();
    /**
     * engineDAO.
     */
    private final EngineDAO engineDAO = EngineDAO.getInstance();
    /**
     * modelDAO.
     */
    private final ModelDAO modelDAO = ModelDAO.getInstance();
    /**
     * transmissionDAO.
     */
    private final TransmissionDAO transmissionDAO = TransmissionDAO.getInstance();
    /**
     * userDAO.
     */
    private final UserDAO userDAO = UserDAO.getInstance();
    /**
     * carDAO.
     */
    private final CarDAO carDAO = CarDAO.getInstance();
    /**
     * imageDAO.
     */
    private final ImageDAO imageDAO = ImageDAO.getInstance();

    /**
     * body.
     */
    private Body body = new Body();
    /**
     * brand.
     */
    private Brand brand = new Brand();
    /**
     *engine.
     */
    private Engine engine = new Engine();
    /**
     *transmission.
     */
    private Transmission transmission = new Transmission();
    /**
     *model.
     */
    private Model model = new Model();
    /**
     *order.
     */
    private Order order = new Order();
    /**
     *image.
     */
    private Image image = new Image();
    /**
     *car.
     */
    private Car car = new Car();
    /**
     *user.
     */
    private User user = new User();

    /**
     * Clear table and fill start data.
     */
    @Before
    public void clearTable() {
        orderDAO.executeQuery("Truncate table orders restart identity and commit no check");

        body.setName("sedan");
        bodyDAO.saveOrUpdate(body);

        brand.setName("BMW");
        brandDAO.saveOrUpdate(brand);

        engine.setName("gas");
        engineDAO.saveOrUpdate(engine);

        model.setName("745i");
        model.setBrand(brand);
        modelDAO.saveOrUpdate(model);

        transmission.setName("auto");
        transmissionDAO.saveOrUpdate(transmission);

        car.setColor("orange");
        car.setBody(body);
        car.setEngine(engine);
        car.setModel(model);
        car.setTransmission(transmission);
        carDAO.saveOrUpdate(car);

        user.setLogin("login");
        user.setPassword("password");
        user.setEmail("email@email");
        userDAO.saveOrUpdate(user);
    }

    /**
     * Test of adding new order.
     * @throws Exception - exc.
     */
    @Test
    public void add() throws Exception {
        order.setDescription("test");
        order.setPrice(1000);
        order.setSold(false);
        order.setDate(new Timestamp(0L));
        order.setUser(user);
        order.setCar(car);
        order.setImages(new ArrayList<>());
        orderDAO.add(order);

        List<Order> result = orderDAO.getAll();
        assertThat(result.get(0), is(order));
    }


    /**
     * Test of getting all orders.
     * @throws Exception - exc.
     */
    @Test
    public void getAll() throws Exception {
        Order firstOrder = new Order();
        firstOrder.setDescription("test1");
        firstOrder.setPrice(1000);
        firstOrder.setSold(false);
        firstOrder.setDate(new Timestamp(0L));
        firstOrder.setUser(user);
        firstOrder.setCar(car);
        firstOrder.setImages(new ArrayList<>());
        orderDAO.saveOrUpdate(firstOrder);

        Order secondOrder = new Order();
        secondOrder.setDescription("test2");
        secondOrder.setPrice(2000);
        secondOrder.setSold(false);
        secondOrder.setDate(new Timestamp(0L));
        secondOrder.setUser(user);
        secondOrder.setCar(car);
        secondOrder.setImages(new ArrayList<>());
        orderDAO.saveOrUpdate(secondOrder);

        List<Order> expected = new ArrayList<>(Arrays.asList(firstOrder, secondOrder));
        List<Order> result = orderDAO.getAll();

        assertThat(result, is(expected));
    }

    /**
     * Test of searching order by id.
     * @throws Exception - exc.
     */
    @Test
    public void getById() throws Exception  {
        Order orderById = new Order();
        orderById.setDescription("test3");
        orderById.setPrice(3000);
        orderById.setSold(false);
        orderById.setDate(new Timestamp(0L));
        orderById.setUser(user);
        orderById.setCar(car);
        orderById.setImages(new ArrayList<>());
        orderDAO.saveOrUpdate(orderById);
        Order result = orderDAO.getById(orderById.getId());

        assertThat(result, is(orderById));
    }

    /**
     * Test of searching order by last day.
     * @throws Exception - exc.
     */
    @Test
    public void getByLastDay() throws Exception {
        Order firstOrder = new Order();
        firstOrder.setDescription("test4");
        firstOrder.setPrice(1000);
        firstOrder.setSold(false);
        firstOrder.setDate(new Timestamp(0L));
        firstOrder.setUser(user);
        firstOrder.setCar(car);
        firstOrder.setImages(new ArrayList<>());
        orderDAO.saveOrUpdate(firstOrder);

        Order secondOrder = new Order();
        secondOrder.setDescription("test5");
        secondOrder.setPrice(2000);
        secondOrder.setSold(false);
        secondOrder.setDate(new Timestamp(System.currentTimeMillis()));
        secondOrder.setUser(user);
        secondOrder.setCar(car);
        secondOrder.setImages(new ArrayList<>());
        orderDAO.saveOrUpdate(secondOrder);
        List<Order> expected = new ArrayList<>(Arrays.asList(secondOrder));
        List<Order> result = getByLastDay(orderDAO.getAll());
        assertThat(result, is(expected));
    }
    /**
     * Test of searching order by having foto.
     * @throws Exception - exc.
     */
    @Test
    public void getByWithFoto() throws Exception {
        Order firstOrder = new Order();
        firstOrder.setDescription("test6");
        firstOrder.setPrice(1000);
        firstOrder.setSold(false);
        firstOrder.setDate(new Timestamp(0L));
        firstOrder.setUser(user);
        firstOrder.setCar(car);
        image.setData("Image".getBytes());
        image.setOrder(firstOrder);
        List<Image> images1 = new ArrayList<>();
        images1.add(image);
        firstOrder.setImages(images1);
        orderDAO.saveOrUpdate(firstOrder);
        //image нужно сохранять после сохранения order, иначе будет ссылка на несохраненный в базе данных объект.
        imageDAO.add(image);

        Order secondOrder = new Order();
        secondOrder.setDescription("test7");
        secondOrder.setPrice(2000);
        secondOrder.setSold(false);
        secondOrder.setDate(new Timestamp(System.currentTimeMillis()));
        secondOrder.setUser(user);
        secondOrder.setCar(car);
        List<Image> images2 = new ArrayList<>();
        secondOrder.setImages(images2);
        orderDAO.saveOrUpdate(secondOrder);
        List<Order> expected = new ArrayList<>(Arrays.asList(firstOrder));
        List<Order> result = getByWithFoto(orderDAO.getAll());
        assertThat(result, is(expected));
    }
    /**
     * Test of searching order by brand.
     * @throws Exception - exc.
     */
    @Test
    public void getByBrand() throws Exception {
        Order firstOrder = new Order();
        firstOrder.setDescription("test8");
        firstOrder.setPrice(1000);
        firstOrder.setSold(false);
        firstOrder.setDate(new Timestamp(0L));
        firstOrder.setUser(user);
        firstOrder.setCar(car);
        firstOrder.setImages(new ArrayList<>());
        orderDAO.add(firstOrder);
        System.out.println("***" + firstOrder);

        Order secondOrder = new Order();
        secondOrder.setDescription("test9");
        secondOrder.setPrice(2000);
        secondOrder.setSold(false);
        secondOrder.setDate(new Timestamp(System.currentTimeMillis()));
        secondOrder.setUser(user);
        secondOrder.setCar(car);
        secondOrder.setImages(new ArrayList<>());
        orderDAO.add(secondOrder);
        System.out.println("***" + secondOrder);

        Order thirdOrder = new Order();
        body.setName("sedan");
        bodyDAO.saveOrUpdate(body);

        Brand brand1 = new Brand();
        brand1.setName("Mers");
        brandDAO.saveOrUpdate(brand1);

        engine.setName("gas");
        engineDAO.saveOrUpdate(engine);

        Model model1 = new Model();
        model1.setName("600S");
        model1.setBrand(brand1);
        modelDAO.saveOrUpdate(model1);

        transmission.setName("auto");
        transmissionDAO.saveOrUpdate(transmission);

        Car car3 = new Car();
        car3.setColor("blue");
        car3.setBody(body);
        car3.setEngine(engine);
        car3.setModel(model1);
        car3.setTransmission(transmission);
        carDAO.saveOrUpdate(car3);
        thirdOrder.setDescription("test9");
        thirdOrder.setPrice(3000);
        thirdOrder.setSold(false);
        thirdOrder.setDate(new Timestamp(System.currentTimeMillis()));
        thirdOrder.setUser(user);
        thirdOrder.setCar(car3);
        thirdOrder.setImages(new ArrayList<>());
        orderDAO.add(thirdOrder);
        for (Order next : orderDAO.getAll()) {
            System.out.println(next);
        }
        List<Order> expected = new ArrayList<>(Arrays.asList(firstOrder, secondOrder));
        List<Order> result = getByBrand(orderDAO.getAll(), "BMW");
        assertThat(result, is(expected));
    }

    /**
     * getByLastDay.
     * @param list - list.
     * @return - list of added orders in last day (from 00:00).
     */
    public List<Order> getByLastDay(List<Order> list) {
        List<Order> newlist = new ArrayList<>();
        int month = LocalDateTime.now().getDayOfMonth();
        for (Order nextOrder : list) {
            if (nextOrder.getDate().toLocalDateTime().getDayOfMonth() == month) {
                newlist.add(nextOrder);
            }
        }
        return newlist;
    }

    /**
     * with foto.
     * @param list - list.
     * @return - with foto.
     */
    public List<Order> getByWithFoto(List<Order> list) {
        List<Order> newlist = new ArrayList<>();
        for (Order nextOrder : list) {
            if (nextOrder.getImages().size() > 0) {
                newlist.add(nextOrder);
            }
        }
        return newlist;
    }

    /**
     * by brand.
     * @param list - list.
     * @param brand - brand.
     * @return - by brand.
     */
    public List<Order> getByBrand(List<Order> list, String brand) {
        List<Order> newlist = new ArrayList<>();
        for (Order nextOrder : list) {
            if (nextOrder.getCar().getModel().getBrand().getName().equals(brand)) {
                newlist.add(nextOrder);
            }
        }
        return newlist;
    }
}