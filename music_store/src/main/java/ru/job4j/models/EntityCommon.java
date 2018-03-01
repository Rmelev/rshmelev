package ru.job4j.models;

public class EntityCommon implements Entity {
    private final int id;

    public EntityCommon(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof Entity) {
            result = this.id == ((Entity) obj).getId();
        }
        return result;
    }
}
