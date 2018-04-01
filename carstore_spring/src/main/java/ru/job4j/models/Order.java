package ru.job4j.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * order.
 */
@Entity
@Table(name = "orders")
public class Order {
    /**
     * id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    /**
     * description.
     */
    @Column(name = "description")
    private String description;
    /**
     *price.
     */
    @Column(name = "price")
    private int price;
    /**
     *sold status.
     */
    @Column(name = "sold", nullable = false)
    private boolean sold;
    /**
     *date.
     */
    @Column(name = "date")
    private Timestamp date;
    /**
     *user.
     */
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;
    /**
     *car.
     */
    @ManyToOne
    @JoinColumn(name = "id_car", nullable = false)
    private Car car;
    /**
     *images.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<Image> images = new CopyOnWriteArrayList<>();
    /**
     * default constructor.
     */
    public Order() {
    }

    /**
     * @return - id
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
     * @return - description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * @param description - description.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * @return - price.
     */
    public int getPrice() {
        return price;
    }
    /**
     * @param price - price.
     */
    public void setPrice(int price) {
        this.price = price;
    }
    /**
     * @return - is sold.
     */
    public boolean isSold() {
        return sold;
    }
    /**
     * @param sold - is sold.
     */
    public void setSold(boolean sold) {
        this.sold = sold;
    }
    /**
     * @return - date.
     */
    public Timestamp getDate() {
        return date;
    }
    /**
     * @param date - date.
     */
    public void setDate(Timestamp date) {
        this.date = date;
    }
    /**
     * @return - user.
     */
    public User getUser() {
        return user;
    }
    /**
     * @param user - user.
     */
    public void setUser(User user) {
        this.user = user;
    }
    /**
     * @return car - car.
     */
    public Car getCar() {
        return car;
    }
    /**
     * @param car - car.
     */
    public void setCar(Car car) {
        this.car = car;
    }
    /**
     * @return - images.
     */
    public List<Image> getImages() {
        return images;
    }
    /**
     * @param images - images.
     */
    public void setImages(List<Image> images) {
        this.images = images;
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
        Order order = (Order) o;
        return id == order.id
                && price == order.price
                && sold == order.sold
                && Objects.equals(description, order.description)
                && Objects.equals(date, order.date)
                && Objects.equals(user, order.user)
                && Objects.equals(car, order.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, price, sold, date, user, car, images);
    }
    /**
     * @return - hashcode.
     */
    @Override
    public String toString() {
        return "Order{"
                + "id=" + id
                + ", description='" + description + '\''
                + ", price=" + price
                + ", sold=" + sold
                + ", date=" + date
                + ", user=" + user
                + ", car=" + car
                + '}';
    }
}
