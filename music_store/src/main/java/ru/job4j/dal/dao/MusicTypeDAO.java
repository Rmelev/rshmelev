package ru.job4j.dal.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.dal.dao.queries.MusicTypeQueries;
import ru.job4j.models.MusicType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MusicTypeDAO implements DAO<MusicType> {
    private static final Logger LOG = LoggerFactory.getLogger(MusicTypeDAO.class);

//    private Connection conn = StoreDataSource.DATA_SOURCE.dbConnection();

    @Override
    public void create(MusicType entity) {
        try (PreparedStatement ps = conn.prepareStatement(MusicTypeQueries.getCREATE())) {
            ps.setString(1, entity.getType());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
    }

    @Override
    public List<MusicType> getAll() {
        List<MusicType> userList = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(MusicTypeQueries.getGetAll()); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int mTypeId = rs.getInt("mtype_id");
                String mtype = rs.getString("mtype");
                userList.add(new MusicType(mTypeId, mtype));
            }
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
        return userList;
    }

    @Override
    public MusicType getById(int id) {
        MusicType type = null;
        try (PreparedStatement ps = conn.prepareStatement(MusicTypeQueries.getGetById())) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String mtype = rs.getString("mtype");
                    type = new MusicType(id, mtype);
                }
            }
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
        return type;
    }

    public int getByType(String mtype) {
        int typeId = -1;
        try (PreparedStatement ps = conn.prepareStatement(MusicTypeQueries.getGetByType())) {
            ps.setString(1, mtype);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    typeId = Integer.valueOf(rs.getString("mtype_id"));
                }
            }
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
        return typeId;
    }

    @Override
    public void edit(MusicType entity) {
        try (PreparedStatement ps = conn.prepareStatement(MusicTypeQueries.getEDIT())) {
            ps.setString(1, entity.getType());
            ps.setInt(2, entity.getId());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement ps = conn.prepareStatement(MusicTypeQueries.getDELETE())) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
    }
}
