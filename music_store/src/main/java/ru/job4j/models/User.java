package ru.job4j.models;

import java.util.List;

public class User extends EntityCommon {

    private final String login;

    private final String password;

    private final Role role;

    private final Address address;

    private final List<MusicType> musicTypes;

    public User(int id, String login, String password, Role role, Address address, List<MusicType> musicTypes) {
        super(id);
        this.login = login;
        this.password = password;
        this.role = role;
        this.address = address;
        this.musicTypes = musicTypes;
    }

    public String getLogin() {
        return this.login;
    }

    public String getPassword() {
        return this.password;
    }

    public Role getRole() {
        return this.role;
    }

    public Address getAddress() {
        return this.address;
    }

    public List<MusicType> getMusicTypes() {
        return this.musicTypes;
    }

    @Override
    public String toString() {
        return String.format("User(%s, %s, %s, %s, %s)",
                this.getLogin(),
                this.getPassword(),
                this.getRole(),
                this.getAddress(),
                this.getMusicTypes()
        );
    }
}
