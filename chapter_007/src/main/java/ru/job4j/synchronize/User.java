package ru.job4j.synchronize;

/**
 * class bank client.
 */
public class User implements Comparable<User> {
    /**
     * client id.
     */
    private final int id;

    /**
     * id getter.
     * @return - id.
     */
    int getId() {
        return id;
    }

    /**
     * User's amount of money.
     */
    private double amount;

    /**
     * getter.
     * @return - amount.
     */
    double getAmount() {
        return amount;
    }

    /**
     * setter.
     * @param amount - amount.
     */
    void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Constructor.
     * @param id - id.
     * @param amount - amount.
     */
    User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    /**
     * @return - string representation.
     */
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", amount=" + amount + '}' + '\n';
    }

    /**
     * @param o - User to compare.
     * @return - >0 if this bigger o.
     */
    @Override
    public int compareTo(User o) {
        return this.id - o.id;
    }
}
