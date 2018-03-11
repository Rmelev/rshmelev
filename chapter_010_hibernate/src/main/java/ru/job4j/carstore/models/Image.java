package ru.job4j.carstore.models;

import java.util.Objects;

public class Image {
    private int id;
    private String url;
    private Order order;

    public Image() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
                Objects.equals(url, image.url) &&
                Objects.equals(order, image.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, order);
    }
}
