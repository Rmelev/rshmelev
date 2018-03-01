package ru.job4j.dal.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.dal.dao.queries.MusicTypeQueries;
import ru.job4j.dal.dao.queries.RoleQueries;
import ru.job4j.models.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO implements DAO<Role> {

    private static final Logger LOG = LoggerFactory.getLogger(RoleDAO.class);

//    private Connection conn = StoreDataSource.DATA_SOURCE.dbConnection();

    @Override
    public void create(Role entity) {
        try (PreparedStatement ps = conn.prepareStatement(RoleQueries.getCREATE())) {
            ps.setString(1, entity.getRole());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
    }

    @Override
    public List<Role> getAll() {
        List<Role> userList = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(RoleQueries.getGetAll()); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int roleId = rs.getInt("role_id");
                String role = rs.getString("role");
                userList.add(new Role(roleId, role));
            }
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
        return userList;
    }

    @Override
    public Role getById(int id) {
        Role roleObj = null;
        try (PreparedStatement ps = conn.prepareStatement(RoleQueries.getGetById())) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String role = rs.getString("role");
                    System.out.println(role);
                    roleObj = new Role(id, role);
                }
            }
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
        return roleObj;
    }

    public int getByRole(String role) {
        int roleId = -1;
        try (PreparedStatement ps = conn.prepareStatement(RoleQueries.getGetByRole())) {
            ps.setString(1, role);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    roleId = rs.getInt("role_id");
                }
            }
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
        return roleId;
    }

    @Override
    public void edit(Role entity) {
        try (PreparedStatement ps = conn.prepareStatement(RoleQueries.getEDIT())) {
            ps.setString(1, entity.getRole());
            ps.setInt(2, entity.getId());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement ps = conn.prepareStatement(RoleQueries.getDELETE())) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
    }
}
