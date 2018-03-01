package ru.job4j.dal.dao.queries;

public class UsersMusicTypesQueries {
    private static final String CREATE = "INSERT INTO comm_user_mtype (user_id, mtype_id) VALUES (?, ?)";
    private static final String GET_BY_ID = "SELECT music_type.mtype_id AS id, music_type.mtype AS type "
            + " FROM (SELECT mtype_id AS id FROM comm_user_mtype WHERE user_id = ?) AS user_music_types "
            + " LEFT JOIN music_type ON user_music_types.id = music_type.mtype_id";
    private static final String GET_ALL = "SELECT user_id, comm_user_mtype.mtype_id, music_type.mtype as type " +
            " FROM comm_user_mtype LEFT JOIN music_type ON comm_user_mtype.mtype_id = music_type.mtype_id ";
    private static final String DELETE = "DELETE FROM comm_user_mtype WHERE comm_id = ?";

    private static final String GET_COMM_ID = "SELECT comm_id FROM comm_user_mtype WHERE user_id = ?";

    public static String getCREATE() {
        return CREATE;
    }

    public static String getGetById() {
        return GET_BY_ID;
    }

    public static String getGetAll() {
        return GET_ALL;
    }

    public static String getDELETE() {
        return DELETE;
    }

    public static String getGetCommId() {
        return GET_COMM_ID;
    }
}
