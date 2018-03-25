package ru.job4j;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * hibernate connection to database.
 */
@Repository
public class HibernateFactory {
    /**
     * Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(HibernateFactory.class);
    /**
     * session factory.
     */
    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    /**
     * getter.
     * @return - factory.
     */
    public static SessionFactory getFactory() {
        return FACTORY;
    }

    /**
     * @throws Exception - exc.
     */
    public void close() throws Exception {
        FACTORY.close();
    }
}
