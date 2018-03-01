package ru.job4j.dal.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.dal.dao.queries.UserQueries;
import ru.job4j.models.UserDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements DAO<UserDTO> {

    private static final Logger LOG = LoggerFactory.getLogger(UserDAO.class);

    @Override
    public void create(UserDTO user) {

    }

    public UserDTO createNew(String login, String password, int roleId, int addressId) {
        UserDTO user = null;
        try (PreparedStatement ps = conn.prepareStatement(UserQueries.getCREATE(), Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, login);
            ps.setString(2, password);
            ps.setInt(3, roleId);
            ps.setInt(4, addressId);
            ps.executeUpdate();
            user = new UserDTO(this.getGeneratedId(ps), login, password, roleId, addressId);
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
        return user;
    }

    @Override
    public List<UserDTO> getAll() {
        List<UserDTO> userList = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(UserQueries.getGetAll()); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("user_id");
                String login = rs.getString("login");
                String password = rs.getString("password");
                int roleId = rs.getInt("role");
                int addressId = rs.getInt("address");
                UserDTO dto = new UserDTO(id, login, password, roleId, addressId);
                System.out.println(dto);
                userList.add(dto);
            }
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
        return userList;
    }

    @Override
    public UserDTO getById(int id) {
        UserDTO user = null;
        try (PreparedStatement ps = conn.prepareStatement(UserQueries.getGetById())) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String login = rs.getString("login");
                    String password = rs.getString("password");
                    int roleId = rs.getInt("role");
                    int addressId = rs.getInt("address");
                    user = new UserDTO(id, login, password, roleId, addressId);
                }
            }
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
        return user;
    }

    public UserDTO getByLogin(String login) {
        UserDTO user = null;
        try (PreparedStatement ps = conn.prepareStatement(UserQueries.getGetByLogin()); ResultSet rs = ps.executeQuery()) {
            ps.setString(1, login);
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String password = rs.getString("password");
                int roleId = rs.getInt("role");
                int addressId = rs.getInt("address");
                user = new UserDTO(userId, login, password, roleId, addressId);
            }
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
        return user;
    }

    public UserDTO getByAddress(String address) {
        UserDTO user = null;
        try (PreparedStatement ps = conn.prepareStatement(UserQueries.getGetByAddress()); ResultSet rs = ps.executeQuery()) {
            ps.setString(1, address);
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String login = rs.getString("login");
                String password = rs.getString("password");
                int roleId = rs.getInt("role");
                int addressId = rs.getInt("address");
                user = new UserDTO(userId, login, password, roleId, addressId);
            }
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
        return user;
    }

    @Override
    public void edit(UserDTO entity) {
        try (PreparedStatement ps = conn.prepareStatement(UserQueries.getEDIT())) {
            ps.setString(1, entity.getLogin());
            ps.setString(2, entity.getPassword());
            ps.setInt(3, entity.getRoleId());
            ps.setInt(4, entity.getAddressId());
            ps.setInt(5, entity.getId());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement ps = conn.prepareStatement(UserQueries.getDELETE())) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
    }

    private int getGeneratedId(Statement st) throws SQLException {
        ResultSet generatedKeys = st.getGeneratedKeys();
        generatedKeys.next();
        return generatedKeys.getInt("user_id");
    }

}
