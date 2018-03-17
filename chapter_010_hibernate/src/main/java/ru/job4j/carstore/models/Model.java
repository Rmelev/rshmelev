package ru.job4j.carstore.models;

import java.util.Objects;
/**
 * car model.
 */
public class Model {
    /**
     * id.
     */
    private int id;
    /**
     * name.
     */
    private String name;
    /**
     * brand.
     */
    private Brand brand;
    /**
     * default constructor.
     */
    public Model() {
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
     * @return - brand.
     */
    public Brand getBrand() {
        return brand;
    }
    /**
     * @param brand - brand.
     */
    public void setBrand(Brand brand) {
        this.brand = brand;
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
        Model model = (Model) o;
        return id == model.id
                && Objects.equals(name, model.name)
                && Objects.equals(brand, model.brand);
    }
    /**
     * @return - hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, brand);
    }
    /**
     * @return - string representation.
     */
    @Override
    public String toString() {
        return "Model{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", brand=" + brand
                + '}';
    }
}
