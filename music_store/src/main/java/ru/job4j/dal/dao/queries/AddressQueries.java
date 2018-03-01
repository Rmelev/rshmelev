package ru.job4j.dal.dao.queries;

public class AddressQueries {
    private static final String CREATE = "INSERT INTO address (city, street, house) VALUES (?, ?, ?)";
    private static final String GET_ALL = "SELECT * FROM address";
    private static final String GET_BY_ID = "SELECT * FROM address WHERE address_id = ?";
    private static final String GET_COND = "SELECT * FROM address";
    private static final String EDIT = " UPDATE address SET city = ?, street = ?, house = ? WHERE address_id = ?";
    private static final String DELETE = "DELETE FROM address WHERE address_id = ?";
    private static final String GET_BY_ADDRESS = "SELECT * FROM address WHERE city = ? AND street = ? AND house = ?";

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

    public static String getGetByAddress() {
        return GET_BY_ADDRESS;
    }
}
