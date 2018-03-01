package ru.job4j.models;

public class Role extends EntityCommon {
    private String role;

    public Role(int id, String role) {
        super(id);
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }

    @Override
    public String toString() {
        return this.getRole();
    }
}
