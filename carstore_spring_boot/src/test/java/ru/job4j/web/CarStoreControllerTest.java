package ru.job4j.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.Application;
import ru.job4j.domain.Order;
import ru.job4j.repository.BrandDAO;
import ru.job4j.repository.OrderDAO;
import org.assertj.core.util.Lists;
import ru.job4j.repository.UserDAO;

import java.util.ArrayList;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CarStoreControllerTest {
    private MockMvc mvc;

    @MockBean
    private OrderDAO orderDAO;
    @MockBean
    private UserDAO userDAO;
    @MockBean
    private BrandDAO brandDAO;

    @Before
    public void setup() {
        this.mvc = standaloneSetup(new CarStoreController(orderDAO, userDAO, brandDAO)).build();
    }

    @Test
    @WithMockUser(username="user")
    public void whenGetOrdersThenPageOrders() throws Exception {
        given(
                this.orderDAO.findAll()
        ).willReturn(
                new ArrayList<>(
                        Lists.newArrayList(new Order())
                )
        );

        this.mvc.perform(
                get("/").accept(MediaType.TEXT_HTML)
        ).andExpect(
                status().isOk()
        ).andExpect(
                view().name("index")
        );
    }
}
