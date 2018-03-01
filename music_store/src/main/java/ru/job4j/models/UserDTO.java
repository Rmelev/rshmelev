package ru.job4j.models;

public class UserDTO extends EntityCommon {

    /**
     * A login.
     */
    private final String login;
    /**
     * A password.
     */
    private final String password;
    /**
     * A role id.
     */
    private final int roleId;
    /**
     * An address id.
     */
    private final int addressId;

    public UserDTO(int id, String login, String password, int roleId, int addressId) {
        super(id);
        this.login = login;
        this.password = password;
        this.roleId = roleId;
        this.addressId = addressId;
    }

    public String getLogin() {
        return this.login;
    }

    public String getPassword() {
        return this.password;
    }

    public int getRoleId() {
        return this.roleId;
    }

    public int getAddressId() {
        return this.addressId;
    }

    @Override
    public String toString() {
        return String.format("User(%s, %s, %s, %s)",
                this.getLogin(),
                this.getPassword(),
                this.getRoleId(),
                this.getAddressId()
        );
    }
}
