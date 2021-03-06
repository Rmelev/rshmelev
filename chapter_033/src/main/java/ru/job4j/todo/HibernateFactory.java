package ru.job4j.todo;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * hibernate connection to database.
 */
public class HibernateFactory implements AutoCloseable {
    /**
     * Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(HibernateFactory.class);
    /**
     * session factory.
     */
    private static final SessionFactory factory = new Configuration().configure().buildSessionFactory();

    /**
     * getter.
     * @return - factory.
     */
    public static SessionFactory getFactory() {
        return factory;
    }

    /**
     * overrided close from Autocloseable.
     * @throws Exception - exc.
     */
    @Override
    public void close() throws Exception {
        factory.close();
    }
}
