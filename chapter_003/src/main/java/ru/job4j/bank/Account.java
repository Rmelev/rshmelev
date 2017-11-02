package ru.job4j.bank;

/**
 * class for one user's account.
 */
public class Account {
    /**
     * money on account.
     */
    private double value;
    /**
     * requisites of account.
     */
    private long requisites;

    /**
     * Constructor.
     * @param value - value.
     * @param requisites - requisites.
     */
    Account(double value, long requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    /**
     * getter.
     * @return - value.
     */
    public double getValue() {
        return value;
    }

    /**
     * setter.
     * @param value - value.
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * @param o - object.
     * @return - true, if equals.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Account account = (Account) o;

        if (Double.compare(account.value, value) != 0) {
            return false;
        }
        return requisites == account.requisites;
    }

    /**
     * @return - hashcode of object.
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(value);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (requisites ^ (requisites >>> 32));
        return result;
    }

    /**
     * @return - string representation.
     */
    @Override
    public String toString() {
        return "value:" + value + " requisites:" + requisites;
    }
}