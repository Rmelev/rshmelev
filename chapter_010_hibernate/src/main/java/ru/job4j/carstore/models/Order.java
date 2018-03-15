package ru.job4j.carstore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public class Order {
    private int id;
    private String description;
    private int price;
    private boolean sold;
    private Timestamp date;
    private User user;
    private Car car;

    @JsonIgnoreProperties("order")
    private List<Image> images = new CopyOnWriteArrayList<>();

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                price == order.price &&
                sold == order.sold &&
                Objects.equals(description, order.description) &&
                Objects.equals(date, order.date) &&
                Objects.equals(user, order.user) &&
                Objects.equals(car, order.car) &&
                Objects.equals(images, order.images);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, price, sold, date, user, car, images);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", sold=" + sold +
                ", date=" + date +
                ", user=" + user +
                ", car=" + car +
                ", images=" + images +
                '}';
    }
}
