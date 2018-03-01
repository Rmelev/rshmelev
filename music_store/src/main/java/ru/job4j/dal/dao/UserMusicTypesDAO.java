package ru.job4j.dal.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.dal.StoreDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import ru.job4j.dal.dao.queries.UsersMusicTypesQueries;
import ru.job4j.models.MusicType;

public class UserMusicTypesDAO {
    private static final Logger LOG = LoggerFactory.getLogger(UserMusicTypesDAO.class);

    private static final Connection conn = StoreDataSource.DATA_SOURCE.dbConnection();

    public void attach(int userId, int mtype) {
        try (PreparedStatement st = conn.prepareStatement(UsersMusicTypesQueries.getCREATE())) {
            st.setInt(1, userId);
            st.setInt(2, mtype);
            st.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public void deleteRecord(int id) {
        try (PreparedStatement st = conn.prepareStatement(UsersMusicTypesQueries.getDELETE())) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public List<MusicType> readMusicTypes(int userId) {
        List<MusicType> result = new ArrayList<>();
        try (PreparedStatement st = conn.prepareStatement(UsersMusicTypesQueries.getGetById())) {
            st.setInt(1, userId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int mTypeId = rs.getInt("id");
                    String mtype = rs.getString("type");
                    result.add(new MusicType(mTypeId, mtype));
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        System.out.println(result);
        return result;
    }

    public List<Integer> readMusicIdTypes(int userId) {
        List<Integer> result = new ArrayList<>();
        try (PreparedStatement st = conn.prepareStatement(UsersMusicTypesQueries.getGetCommId())) {
            st.setInt(1, userId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int mTypeId = rs.getInt("comm_id");
                    result.add(mTypeId);
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        System.out.println(result);
        return result;
    }

    public Map<Integer, List<MusicType>> readMusicTypes() {
        Map<Integer, List<MusicType>> result = new HashMap<>();
        try (PreparedStatement st = conn.prepareStatement(UsersMusicTypesQueries.getGetAll())) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                int mTypeId = rs.getInt("mtype_id");
                String mtype = rs.getString("mtype");
                result.putIfAbsent(userId, new ArrayList<>());
                result.get(userId).add(new MusicType(mTypeId, mtype));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
}
