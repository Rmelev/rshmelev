package ru.job4j.dal.dao.queries;

public class MusicTypeQueries {
    private static final String CREATE = "INSERT INTO music_type (mtype) VALUES (?)";
    private static final String GET_ALL = "SELECT * FROM music_type";
    private static final String GET_BY_ID = "SELECT * FROM music_type WHERE id = ?";
    private static final String GET_COND = "SELECT * FROM music_type";
    private static final String EDIT = " UPDATE music_type SET mtype = ? WHERE mtype_id = ?";
    private static final String DELETE = "DELETE FROM music_type WHERE id = ?";
    private static final String GET_BY_TYPE = "SELECT * FROM music_type WHERE mtype = ?";

    public static String getCREATE() {
        return CREATE;
    }

    public static String getGetAll() {
        return GET_ALL;
    }

    public static String getGetById() {
        return GET_BY_ID;
    }

    public static String getGetCond() {
        return GET_COND;
    }

    public static String getEDIT() {
        return EDIT;
    }

    public static String getDELETE() {
        return DELETE;
    }

    public static String getGetByType() {
        return GET_BY_TYPE;
    }
}
