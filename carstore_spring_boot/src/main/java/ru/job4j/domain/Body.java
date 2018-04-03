package ru.job4j.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * car body.
 */
@Entity
@Table(name = "body")
public class Body {
    /**
     * id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    /**
     * name.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * default constructor.
     */
    public Body() {
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
     * @return - name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name - name.
     */
    public void setName(String name) {
        this.name = name;
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
        Body body = (Body) o;
        return id == body.id
                && Objects.equals(name, body.name);
    }

    /**
     * @return - hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    /**
     * @return - string representation.
     */
    @Override
    public String toString() {
        return "Body{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
