package ru.job4j.dal.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.dal.StoreDataSource;
import ru.job4j.dal.dao.AddressDAO;
import ru.job4j.dal.dao.MusicTypeDAO;
import ru.job4j.dal.dao.RoleDAO;
import ru.job4j.dal.dao.UserDAO;
import ru.job4j.dal.dao.UserMusicTypesDAO;
import ru.job4j.dal.dao.queries.DataBasesCreateQueries;
import ru.job4j.models.Address;
import ru.job4j.models.User;
import ru.job4j.models.UserDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class StoreRepository {
    private static final Logger LOG = LoggerFactory.getLogger(StoreRepository.class);

    public static final StoreRepository INSTANCE = new StoreRepository();

    private Connection conn;

    private UserDAO userDAO = new UserDAO();

    private AddressDAO addressDAO = new AddressDAO();

    private RoleDAO roleDAO = new RoleDAO();

    private MusicTypeDAO musicTypeDAO = new MusicTypeDAO();

    private UserMusicTypesDAO userMusicTypesDAO = new UserMusicTypesDAO();

    private StoreRepository() {
        conn = StoreDataSource.DATA_SOURCE.dbConnection();
    }

    public void create(String login, String password, String roleId, String addressId, String mtype1, String mtype2, String mtype3) {
        try {
            boolean autocommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            Address address = addressDAO.createNewAddress(addressId);
            UserDTO user = this.userDAO.createNew(login, password, roleDAO.getByRole(roleId), address.getId());
            this.userMusicTypesDAO.attach(user.getId(), musicTypeDAO.getByType(mtype1));
            this.userMusicTypesDAO.attach(user.getId(), musicTypeDAO.getByType(mtype2));
            this.userMusicTypesDAO.attach(user.getId(), musicTypeDAO.getByType(mtype3));
            conn.commit();
            conn.setAutoCommit(autocommit);
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                LOG.error(e1.getMessage(), e1);
            }
            LOG.error(e.getMessage(), e);
        }

    }

    public void delete(int id) {
        try {
            boolean autocommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            List<Integer> musicTypeList = userMusicTypesDAO.readMusicIdTypes(id);
            UserDTO userDTO = userDAO.getById(id);
            int addressId = userDTO.getAddressId();
            for (Integer next : musicTypeList) {
                userMusicTypesDAO.deleteRecord(next);
            }
            userDAO.delete(id);
            addressDAO.deleteRecord(addressId);
            conn.commit();
            conn.setAutoCommit(autocommit);
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                LOG.error(e1.getMessage(), e1);
            }
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * @return a list of all users
     */
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        try {
            boolean autocommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            List<UserDTO> userNotCompleteList = userDAO.getAll();
            for (UserDTO next : userNotCompleteList) {
                User us = new User(
                        next.getId(), next.getLogin(), next.getPassword(), roleDAO.getById(next.getRoleId()), addressDAO.getById(next.getAddressId()),
                        userMusicTypesDAO.readMusicTypes(next.getId()));
                userList.add(us);
            }
            conn.commit();
            conn.setAutoCommit(autocommit);
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                LOG.error(e1.getMessage(), e1);
            }
            LOG.error(e.getMessage(), e);
        }
        return userList;
    }


    public boolean isCredentional(String login, String password) {
        boolean exists = false;
        for (User nextUser : getAll()) {
            if (nextUser.getLogin().equals(login) && nextUser.getPassword().equals(password)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    /**
     * create table method.
     */
    public void dataBasesCreate() {
        try (Statement st = StoreDataSource.DATA_SOURCE.dbConnection().createStatement()) {
            boolean autocommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            st.execute(DataBasesCreateQueries.getCreateRoles());
            st.execute(DataBasesCreateQueries.getCreateMusicTypes());
            st.execute(DataBasesCreateQueries.getCreateUsers());
            st.execute(DataBasesCreateQueries.getCreateAddresses());
            st.execute(DataBasesCreateQueries.getCreateUserMusicTypes());
            conn.commit();
            conn.setAutoCommit(autocommit);
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                LOG.error(e1.getMessage(), e1);
            }
            LOG.error(e.getMessage(), e);
        }
    }
}
