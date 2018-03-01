package ru.job4j.dal.dao.queries;

public class DataBasesCreateQueries {
    private static final String CREATE_USERS = "CREATE TABLE IF NOT EXISTS users ("
            + " user_id serial PRIMARY KEY, "
            + " login VARCHAR(255) UNIQUE, "
            + " name VARCHAR(255), "
            + " password VARCHAR(255), "
            + " role INTEGER REFERENCES roles(role_id), "
            + " address INTEGER UNIQUE "
            + " )";
    private static final String CREATE_ROLES = "CREATE TABLE IF NOT EXISTS roles ("
            + " role_id serial PRIMARY KEY, "
            + " role VARCHAR(255) UNIQUE "
            + ")";
    private static final String CREATE_ADDRESSES = "CREATE TABLE IF NOT EXISTS address ("
            + " address_id serial PRIMARY KEY, "
            + " user_id INTEGER REFERENCES users(user_id), "
            + " city VARCHAR(255), "
            + " street VARCHAR(255), "
            + " house VARCHAR(255) "
            + ")";
    private static final String CREATE_MUSIC_TYPES = "CREATE TABLE IF NOT EXISTS music_type ("
            + " mtype_id serial PRIMARY KEY, "
            + " mtype VARCHAR(255) UNIQUE "
            + ")";
    private static final String CREATE_USER_MUSIC_TYPES = "CREATE TABLE IF NOT EXISTS comm_user_mtype ("
            + " comm_id serial PRIMARY KEY, "
            + " user_id INTEGER REFERENCES users(user_id), "
            + " mtype_id INTEGER REFERENCES music_type(mtype_id) "
            + ")";

    public static String getCreateUsers() {
        return CREATE_USERS;
    }

    public static String getCreateRoles() {
        return CREATE_ROLES;
    }

    public static String getCreateAddresses() {
        return CREATE_ADDRESSES;
    }

    public static String getCreateMusicTypes() {
        return CREATE_MUSIC_TYPES;
    }

    public static String getCreateUserMusicTypes() {
        return CREATE_USER_MUSIC_TYPES;
    }
}
