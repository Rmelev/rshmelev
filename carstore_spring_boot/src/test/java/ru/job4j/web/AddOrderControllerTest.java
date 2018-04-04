package ru.job4j.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.domain.*;
import ru.job4j.repository.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.data.repository.support.Repositories;

@RunWith(SpringRunner.class)
@WebMvcTest(AddOrderController.class)
public class AddOrderControllerTest {
    @Autowired
    private MockMvc mvc;

    /**
     * orderDAO.
     */
    @MockBean
    private OrderDAO orderDAO;
    /**
     * brandDAO.
     */
    @MockBean
    private BrandDAO brandDAO;
    /**
     * modelDAO.
     */
    @MockBean
    private ModelDAO modelDAO;
    /**
     * engineDAO.
     */
    @MockBean
    private EngineDAO engineDAO;
    /**
     * transmissionDAO.
     */
    @MockBean
    private TransmissionDAO transmissionDAO;
    /**
     * bodyDAO.
     */
    @MockBean
    private BodyDAO bodyDAO;
    /**
     * imageDAO.
     */
    @MockBean
    private ImageDAO imageDAO;
    /**
     * carDAO.
     */
    @MockBean
    private CarDAO carDAO;

    @MockBean
    private UserDAO userDAO;

    @MockBean
    private Repositories repositories;

    @Test
    @WithMockUser(username = "gora", roles = "ADMIN")
    public void whenSaveOrderThenHaveSavedOrder() throws Exception {
        Brand mockBrand = new Brand();
        Model mockModel = new Model();
        mockModel.setBrand(mockBrand);
        Body mockBody = new Body();
        Transmission mockTransmission = new Transmission();
        Engine mockEngine = new Engine();
        Car mockCar = new Car();
        mockCar.setColor("red");
        mockCar.setModel(mockModel);
        mockCar.setBody(mockBody);
        mockCar.setTransmission(mockTransmission);
        mockCar.setEngine(mockEngine);
        Order mockOrder = new Order();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        mockOrder.setDate(timestamp);
        mockOrder.setDescription("test dest");
        mockOrder.setPrice(50000);
        mockOrder.setSold(true);
        User mockUser = new User();
        mockUser.setLogin("gora");
        mockUser.setPassword("123");
        mockUser.setEmail("a@a");
        Role mockRole = new Role();
        mockRole.setName("ADMIN");
        mockOrder.setUser(new User());
        given(
                this.carDAO.save(mockCar)
        ).willReturn(
                mockCar
        );
        given(
                this.orderDAO.save(mockOrder)
        ).willReturn(
                mockOrder
        );
        given(
                this.userDAO.findUserByLogin("gora")
        ).willReturn(
                mockUser
        );
        given(
                repositories.getRepositoryFor(Body.class)
        ).willReturn(
                Optional.of(bodyDAO)
        );

        when(orderDAO.findAll()).thenReturn(ImmutableList.of());
        orderDAO.findAll();
        verify(orderDAO, times(1)).findAll();
    }

    @Test
    @WithMockUser(username = "gora")
    public void whenAddControllerThenAddHtml() throws Exception {
        this.mvc.perform(
                get("/add").accept(MediaType.TEXT_HTML)
        ).andExpect(
                status().isOk()
        ).andExpect(
                view().name("add")
        );
    }

    @Test
    @WithMockUser(username = "gora")
    public void whenGetBrandsControllerThenGetBrands() throws Exception {
        List<Brand> list = new ArrayList<>();

        ObjectMapper expected = new ObjectMapper();
        String exp = expected.writeValueAsString(list);
        this.mvc.perform(
                get("/getBrands").accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
        ).andExpect(
                status().isOk()
        ).andExpect(
                content().string(exp)
        );
    }
}
