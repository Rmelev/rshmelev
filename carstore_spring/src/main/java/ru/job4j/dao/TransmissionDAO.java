package ru.job4j.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.models.Transmission;

/**
 * transmission DAO.
 */
@Repository
public interface TransmissionDAO extends CrudRepository<Transmission, Integer> {
    /**
     * get Transmission By Name.
     * @param name - name.
     * @return - transmission.
     */
    Transmission getTransmissionByName(String name);
}