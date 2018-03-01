package ru.job4j.dal.dao.queries;

public class UserQueries {
    private static final String CREATE = "INSERT INTO users (login, password, role, address) VALUES (?, ?, ?, ?)";
    private static final String GET_ALL = "SELECT * FROM users";
    private static final String GET_BY_ID = "SELECT * FROM users WHERE user_id = ?";
    private static final String GET_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
    private static final String GET_BY_ADDRESS = "SELECT * FROM users WHERE address = ?";
    private static final String GET_COND = "SELECT * FROM users";
    private static final String EDIT = " UPDATE users SET login = ?, password = ?, role = ?, address = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM users WHERE user_id = ?";

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

    public static String getGetByLogin() {
        return GET_BY_LOGIN;
    }

    public static String getGetByAddress() {
        return GET_BY_ADDRESS;
    }
}

