package ru.job4j.models;

public class Address extends EntityCommon {
    private final String city;

    private final String street;

    private final String house;

    public Address(int id, String city, String street, String house) {
        super(id);
        this.city = city;
        this.street = street;
        this.house = house;
    }

    public String getCity() {
        return this.city;
    }

    public String getStreet() {
        return this.street;
    }

    public String getHouse() {
        return this.house;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s", this.city, this.street, this.house);

    }
}
