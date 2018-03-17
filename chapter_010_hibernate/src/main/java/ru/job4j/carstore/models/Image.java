package ru.job4j.carstore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;
import java.util.Objects;

/**
 * car image.
 */
public class Image {
    /**
     * id.
     */
    private int id;
    /**
     * image as byte array.
     */
    private byte[] data;
    /**
     * order.
     */
    @JsonIgnoreProperties("images")
    private Order order;
    /**
     * default constructor.
     */
    public Image() {
    }
    /**
     * @return - id.
     */
    public int getId() {
        return id;
    }
    /**
     * @param id - id.
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * @return - data.
     */
    public byte[] getData() {
        return data;
    }
    /**
     * @param data - data.
     */
    public void setData(byte[] data) {
        this.data = data;
    }

    /**
     * @return - order.
     */
    public Order getOrder() {
        return order;
    }

    /**
     * @param order - order.
     */
    public void setOrder(Order order) {
        this.order = order;
    }
    /**
     * @param o - obj.
     * @return - true, if equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Image image = (Image) o;
        return id == image.id
                && Arrays.equals(data, image.data)
                && Objects.equals(order, image.order);
    }
    /**
     * @return - hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, data, order);
    }
}
