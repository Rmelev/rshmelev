package ru.job4j.carstore.models;

import java.util.Objects;

public class Brand {
    private int id;
    private String name;

    public Brand() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brand brand = (Brand) o;
        return id == brand.id &&
                Objects.equals(name, brand.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
