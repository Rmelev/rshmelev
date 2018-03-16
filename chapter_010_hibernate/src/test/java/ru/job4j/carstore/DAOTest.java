package ru.job4j.carstore;

import org.junit.Test;
import ru.job4j.carstore.dao.BodyDAO;
import ru.job4j.carstore.models.Body;

import java.sql.Timestamp;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DAOTest {
    @Test
    public void returnDataFromDataBase() {
        BodyDAO bodyDAO = new BodyDAO();
        Body body1 = new Body();
        body1.setId(1);
        body1.setName("coupe");
        bodyDAO.add(body1);
        assertThat(bodyDAO.getByName("coupe").getId(), is(1));
        assertThat(bodyDAO.getById(1).getName(), is("coupe"));
    }

    @Test
    public void jfjnnfnlfnlknfkl() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp.toLocalDateTime().getDayOfMonth());
    }
}
