package ru.job4j.dal.dao.queries;

public class RoleQueries {
    private static final String CREATE = "INSERT INTO roles (role) VALUES (?)";
    private static final String GET_ALL = "SELECT * FROM roles";
    private static final String GET_BY_ID = "SELECT * FROM roles WHERE role_id = ?";
    private static final String GET_COND = "SELECT * FROM roles";
    private static final String EDIT = " UPDATE roles SET role = ? WHERE role_id = ?";
    private static final String DELETE = "DELETE FROM roles WHERE role_id = ?";
    private static final String GET_BY_ROLE = "SELECT * FROM roles WHERE role = ?";

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

    public static String getGetByRole() {
        return GET_BY_ROLE;
    }
}
