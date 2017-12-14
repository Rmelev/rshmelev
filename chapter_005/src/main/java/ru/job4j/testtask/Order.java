package ru.job4j.testtask;

/**
 * one order information.
 */
public class Order {
    /**
     * id.
     */
    private String id;

    /**
     * getter.
     * @return - id.
     */
    public String getId() {
        return this.id;
    }

    /**
     * operation: buy or sell.
     */
    private boolean operation;

    /**
     * getter.
     * @return - true == buy, false == sell.
     */
    boolean isOperation() {
        return operation;
    }

    /**
     * price.
     */
    private double price;

    /**
     * getter.
     * @return - price.
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * volume (number of lots).
     */
    private int volume;

    /**
     * getter.
     * @return - volume.
     */
    public int getVolume() {
        return this.volume;
    }

    /**
     * create order with new set volume.
     * @param vol - volume.
     * @return - new Order with new volume.
     */
    public Order setVolume(Integer vol) {
        return new Order(this.id, this.operation, this.price, vol);
    }

    /**
     * setter. Set new volume in existing order. Void method.
     * @param vol - new volume.
     */
    public void setVol(int vol) {
        this.volume = vol;
    }

    /**
     * Constructor.
     * @param id - id.
     * @param operation - operation.
     * @param price - price.
     * @param volume - volume.
     */
    public Order(String id, boolean operation, double price, int volume) {
        this.id = id;
        this.operation = operation;
        this.price = price;
        this.volume = volume;
    }

    /**
     * @return - String representation.
     */
    @Override
    public String toString() {
        return "price=" + price + (operation ? ", buy" : ", sell")
                + ", volume=" + volume + '\n';
    }

    /**
     * @param o - object for compare.
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
        Order order = (Order) o;
        if (operation != order.operation) {
            return false;
        }
        if (Double.compare(order.price, price) != 0) {
            return false;
        }
        if (volume != order.volume) {
            return false;
        }
        return id != null ? id.equals(order.id) : order.id == null;
    }

    /**
     * @return - number representation of hashcode.
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (operation ? 1 : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + volume;
        return result;
    }
}
