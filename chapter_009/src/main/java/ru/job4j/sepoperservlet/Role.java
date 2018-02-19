package ru.job4j.sepoperservlet;

/**
 * enum of roles.
 */
public enum Role {
    /**
     * role "admin".
     */
    ADMIN {
        @Override
        public String toString() {
            return "admin";
        }
    },
    /**
     * role "user".
     */
    USER {
        @Override
        public String toString() {
            return "user";
        }
    };

    /**
     * Constructor.
     * @param name - name.
     * @return - role.
     */
    public static Role getRole(String name) {
        Role role = null;
        if ("admin".equalsIgnoreCase(name)) {
            role = ADMIN;
        } else if ("user".equalsIgnoreCase(name)) {
            role = USER;
        }
        return role;
    }
}
