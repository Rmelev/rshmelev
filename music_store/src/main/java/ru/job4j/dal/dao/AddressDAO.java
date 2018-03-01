package ru.job4j.dal.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.dal.dao.queries.AddressQueries;
import ru.job4j.dal.dao.queries.RoleQueries;
import ru.job4j.dal.dao.queries.UsersMusicTypesQueries;
import ru.job4j.models.Address;
import sun.nio.cs.StreamEncoder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AddressDAO implements DAO<Address> {
    private static final Logger LOG = LoggerFactory.getLogger(AddressDAO.class);

//    private Connection conn = StoreDataSource.DATA_SOURCE.dbConnection();

    public Address createNewAddress(String address) {
        Address addressObj = null;
        try (PreparedStatement ps = conn.prepareStatement(AddressQueries.getCREATE(), Statement.RETURN_GENERATED_KEYS)) {
            String[] addressArray = address.split(", ");
            ps.setString(1, addressArray[0]);
            ps.setString(2, addressArray[1]);
            ps.setString(3, addressArray[2]);
            ps.executeUpdate();
            int key = this.getGeneratedId(ps);
            addressObj = new Address(key, addressArray[0], addressArray[1], addressArray[2]);
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
        return addressObj;
    }

    public void deleteRecord(int id) {
        try (PreparedStatement st = conn.prepareStatement(AddressQueries.getDELETE())) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void create(Address entity) {

    }

    @Override
    public List<Address> getAll() {
        List<Address> userList = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(AddressQueries.getGetAll()); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int addressId = rs.getInt("address_id");
                int userId = rs.getInt("user_id");
                String city = rs.getString("city");
                String street = rs.getString("street");
                String house = rs.getString("house");
                userList.add(new Address(addressId, city, street, house));
            }
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
        return userList;
    }

    @Override
    public Address getById(int id) {
        Address address = null;
        try (PreparedStatement ps = conn.prepareStatement(AddressQueries.getGetById())) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String city = rs.getString("city");
                    String street = rs.getString("street");
                    String house = rs.getString("house");
                    address = new Address(id, city, street, house);
                }
            }
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
        return address;
    }

    public int getByAddress(String address) {
        int addressId = -1;
        try (PreparedStatement ps = conn.prepareStatement(AddressQueries.getGetByAddress())) {
            String[] addressArray = address.split(", ");
            ps.setString(1, addressArray[0]);
            ps.setString(2, addressArray[1]);
            ps.setString(3, addressArray[2]);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    addressId = rs.getInt("address_id");
                }
            }
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
        return addressId;
    }

    @Override
    public void edit(Address entity) {
        try (PreparedStatement ps = conn.prepareStatement(AddressQueries.getEDIT())) {
            ps.setString(1, entity.getCity());
            ps.setString(2, entity.getStreet());
            ps.setString(3, entity.getHouse());
            ps.setInt(4, entity.getId());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement ps = conn.prepareStatement(AddressQueries.getDELETE())) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
    }

    private int getGeneratedId(Statement st) throws SQLException {
        ResultSet generatedKeys = st.getGeneratedKeys();
        generatedKeys.next();
        return generatedKeys.getInt("address_id");
    }
}
