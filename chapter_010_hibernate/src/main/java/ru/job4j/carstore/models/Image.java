package ru.job4j.carstore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;
import java.util.Objects;

public class Image {
    private int id;
    private byte[] data;

    @JsonIgnoreProperties("images")
    private Order order;

    public Image() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return id == image.id &&
                Arrays.equals(data, image.data) &&
                Objects.equals(order, image.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, data, order);
    }
}
